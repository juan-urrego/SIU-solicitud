package com.solicitud.solicitud.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.security.dto.JwtDto;
import com.solicitud.solicitud.security.dto.LoginUserDto;
import com.solicitud.solicitud.security.dto.NewUserDto;
import com.solicitud.solicitud.security.entity.User;
import com.solicitud.solicitud.security.enums.RoleName;
import com.solicitud.solicitud.security.jwt.JwtProvider;
import com.solicitud.solicitud.security.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    final
    AuthenticationManager authenticationManager;

    final
    UserService userService;

    final
    JwtProvider jwtProvider;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/new")
    public ResponseEntity<Message> nuevo(@RequestParam("user") String model,
                                         @RequestParam(value = "imageFile") MultipartFile file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        NewUserDto newUserDto = mapper.readValue(model, NewUserDto.class);
        if(StringUtils.isBlank(newUserDto.getEmail()))
            return new ResponseEntity<>(new Message("email is required"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(newUserDto.getPassword()))
            return new ResponseEntity<>(new Message("password is required"), HttpStatus.BAD_REQUEST);
        if(userService.existsByEmail(newUserDto.getEmail()))
            return new ResponseEntity<>(new Message("email already exists"), HttpStatus.BAD_REQUEST);
        userService.saveUser(newUserDto, file);
        return new ResponseEntity<>(new Message("user saved"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("wrong fields"), HttpStatus.BAD_REQUEST);
        if (!userService.existsByEmail(loginUserDto.getEmail()))
            return new ResponseEntity<>(new Message("invalid email or password"), HttpStatus.BAD_REQUEST);
        if (userService.existsActiveByEmail(loginUserDto.getEmail()))
            return new ResponseEntity<>(new Message("Esta cuenta se encuentra inactiva, no es posible acceder"), HttpStatus.FORBIDDEN);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> list(){
        List<User> list = userService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        Optional<User> user = userService.getUserById(id);
        if(user.isPresent())
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        return new ResponseEntity<>(new Message("user does not exist with this id or not found"), HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteById(@PathVariable("id")int id){
        if(!userService.existsById(id))
            return new ResponseEntity<>(new Message("user does not exist with this id"), HttpStatus.NOT_FOUND);
        userService.delete(id);
        return new ResponseEntity<>(new Message("user deleted"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@RequestParam("user") String model,
                                          @RequestParam(value = "imageFile", required = false) MultipartFile file,
                                          @PathVariable("id")int id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        NewUserDto newUserDto = mapper.readValue(model, NewUserDto.class);
        if(!userService.existsById(id))
            return new ResponseEntity<>(new Message("user does not exist with this id"), HttpStatus.NOT_FOUND);
        if(userService.existsByEmail(newUserDto.getEmail()) && userService.getUserByEmail(newUserDto.getEmail()).isPresent())
            if(id != userService.getUserByEmail(newUserDto.getEmail()).get().getId())
                return new ResponseEntity<>(new Message("email already exists"), HttpStatus.BAD_REQUEST);
        userService.update(newUserDto, file, id);
        return new ResponseEntity<>(new Message("user updated"), HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/directorActive")
    public ResponseEntity<?> getDirectorActive(){
        Optional<User> directorActive = userService.getUserByDirectorActive();
        if(directorActive.isPresent())
            return new ResponseEntity<>(directorActive.get(), HttpStatus.OK);
        return new ResponseEntity<>(new Message("user not found"), HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value= "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImageById(@PathVariable("id") int id){
        if (!userService.existsById(id))
            return new ResponseEntity<>(new Message("user does not exist with this id"), HttpStatus.NOT_FOUND);
        FileSystemResource file = userService.getImageById(id);
        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/isActive/{id}")
    public ResponseEntity<?> getDirectorBySignature(@PathVariable("id")int id,
                                                    @RequestParam("isActive") boolean isActive){
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()){
            if (user.get().getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_DIRECTOR)))
                return new ResponseEntity<>(new Message("No es posible modificar director, siempre debe de haber un director activo"), HttpStatus.BAD_REQUEST);
            if (user.get().getEmail().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
                return new ResponseEntity<>(new Message("No es posible modificar, mientras se está en sesión con esta cuenta"), HttpStatus.BAD_REQUEST);
            user.get().setActive(isActive);
            userService.save(user.get());
            return new ResponseEntity<>(new Message("usuario guardado"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("No existe un usuario con esa id"), HttpStatus.NOT_FOUND);
    }



    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}
