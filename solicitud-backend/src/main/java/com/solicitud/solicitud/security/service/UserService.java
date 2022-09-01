package com.solicitud.solicitud.security.service;

import com.solicitud.solicitud.repository.FileSystemRepository;
import com.solicitud.solicitud.security.dto.JwtDto;
import com.solicitud.solicitud.security.dto.LoginUserDto;
import com.solicitud.solicitud.security.entity.Role;
import com.solicitud.solicitud.security.entity.User;
import com.solicitud.solicitud.security.enums.RoleName;
import com.solicitud.solicitud.security.jwt.JwtProvider;
import com.solicitud.solicitud.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

    final
    UserRepository userRepository;

    final
    FileSystemRepository fileSystemRepository;

    final
    RoleService roleService;

    final
    AuthenticationManager authenticationManager;

    final
    JwtProvider jwtProvider;

    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       FileSystemRepository fileSystemRepository,
                       RoleService roleService,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.fileSystemRepository = fileSystemRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }


    public User getUserById(int id){
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "user does not exist with this id or not found"));
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "user does not exist with this email, or not found"));
    }

    public Optional<User> getUserByRoleActive(Optional<Role> role) {
        Set<Role> roles = new HashSet<>();
        role.ifPresent(roles::add);
        return userRepository.findByActiveAndRolesIn(true, roles);
    }

    public Optional<User> getDirectorActive(){
        Optional<Role> roleDirector = roleService.getByRoleName(RoleName.ROLE_DIRECTOR);
        return getUserByRoleActive(roleDirector);
    }

    public boolean existsByEmail(final String email){
        return userRepository.existsByEmail(email);
    }

    public boolean existsActiveByEmail(String email) {
        return userRepository.existsByActiveFalseAndEmail(email);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void saveUser(String name, String email, String position, String password, String role, MultipartFile file) throws Exception {
        if (!getAll().isEmpty() &&
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you must be admin user and be authenticated");
        if (existsByEmail(email))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email already exists");
        User user = new User(name,
                        email,
                        position,
                        true,
                        passwordEncoder.encode(password),
                        saveImage(file.getBytes(),file.getOriginalFilename()));
        user.setRoles(getRoles(role));
        save(user);
    }

    public void update(int id, String name, String email, String position, String password, MultipartFile file) throws  Exception {
        User user = getUserById(id);
        if(existsByEmail(email) && !user.getEmail().equals(email))
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "email already exists");
        user.setName(name);
        user.setEmail(email);
        user.setPosition(position);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(password));
        if(!getImageById(id).exists() && file != null) {
            user.setSignatureUrl(saveImage(file.getBytes(),file.getOriginalFilename()));
        }
        if(file != null && getImageById(id).exists()){
            deleteImageByPath(user.getSignatureUrl());
            user.setSignatureUrl(saveImage(file.getBytes(),file.getOriginalFilename()));
        }
        save(user);
    }

    public void delete(int id){
        User user = getUserById(id);
        if (user.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_ADMIN)) ||
                user.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_DIRECTOR)))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you can not delete this user");
        userRepository.delete(user);
    }

    public JwtDto login(LoginUserDto loginUserDto) {
        if (existsActiveByEmail(loginUserDto.getEmail()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "this user is not longer active");
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return new JwtDto(jwt);
    }

    public JwtDto refreshToken(JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        return new JwtDto(token);
    }

    private Set<Role> getRoles(String role) {
        Optional<Role> roleUser = roleService.getByRoleName(RoleName.ROLE_USER);
        Optional<Role> roleAdmin = roleService.getByRoleName(RoleName.ROLE_ADMIN);
        Optional<Role> roleDirector = roleService.getByRoleName(RoleName.ROLE_DIRECTOR);
        Set<Role> roles = new HashSet<>();
        if(role.equals("admin")) {
            roleAdmin.ifPresent(roles::add);
            Optional<User> userAdmin = getUserByRoleActive(roleAdmin);
            if (userAdmin.isPresent())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "admin user already exists, can not have more than one admin user");
        }
        else if (role.equals("director")) {
            roleDirector.ifPresent(roles::add);
            Optional<User> userDirector = getUserByRoleActive(roleDirector);
            if (userDirector.isPresent()){
                userDirector.get().setActive(false);
                userRepository.save(userDirector.get());
            }
        }
        else {
            roleUser.ifPresent(roles::add);
        }
        return(roles);
    }

    public void activeUser(int id, boolean isActive) {
        User user = getUserById(id);
        if (user.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_DIRECTOR)))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "It is not possible to modify a user director, it always must have an active director");
        if (user.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_ADMIN)))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "It is not possible to modify a user admin, it always must have an active admin");
        if (user.getEmail().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "It is not possible to modify, if you are logged in the same user account");
        user.setActive(isActive);
        save(user);
    }

    public String saveImage(byte[] bytes, String name) throws Exception{
        return fileSystemRepository.saveImageFileSystem(bytes, name);
    }

    public FileSystemResource getImageById(int id) {
        User user = getUserById(id);
        return fileSystemRepository.findInFileSystem(user.getSignatureUrl());
    }

    public void deleteImageByPath(String location){
        fileSystemRepository.deleteByPath(location);
    }



}
