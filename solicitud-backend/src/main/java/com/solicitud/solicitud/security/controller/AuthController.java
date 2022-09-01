package com.solicitud.solicitud.security.controller;

import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.security.dto.JwtDto;
import com.solicitud.solicitud.security.dto.LoginUserDto;
import com.solicitud.solicitud.security.entity.User;
import com.solicitud.solicitud.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    final
    UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public ResponseEntity<Message> create(@RequestParam(value = "name") String name,
                       @RequestParam(value = "email") String email,
                       @RequestParam(value = "position") String position,
                       @RequestParam(value = "password") String password,
                       @RequestParam(value = "role") String role,
                       @RequestParam(value = "imageFile") MultipartFile file) throws Exception {
        userService.saveUser(name, email, position, password, role, file);
        return  new ResponseEntity<>(new Message("user created"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginUserDto loginUserDto){
        JwtDto jwtDto= userService.login(loginUserDto);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAll(){
        List<User> list = userService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getById(@PathVariable(value = "id") int id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("user/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable(value = "email") String email) {
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable(value = "id") int id){
        userService.delete(id);
        return new ResponseEntity<>(new Message("user deleted"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id,
                                          @RequestParam(value = "name") String name,
                                          @RequestParam(value = "email") String email,
                                          @RequestParam(value = "position") String position,
                                          @RequestParam(value = "password") String password,
                                          @RequestParam(value = "imageFile", required = false) MultipartFile file) throws Exception {
        userService.update(id, name, email, position, password, file);
        return new ResponseEntity<>(new Message("user updated"), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/directorActive")
    public ResponseEntity<?> getDirectorActive(){
        User user = userService.getDirectorActive()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "user not found"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value= "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImageById(@PathVariable(value = "id") int id){
        FileSystemResource file = userService.getImageById(id);
        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/isActive/{id}")
    public ResponseEntity<Message> activeUser(@PathVariable( value = "id") int id,
                           @RequestParam( value = "isActive") boolean isActive){
        userService.activeUser(id, isActive);
        return new ResponseEntity<>(new Message("user modified"), HttpStatus.OK);
    }

    //Token refreshing method
    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        JwtDto jwt = userService.refreshToken(jwtDto);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}
