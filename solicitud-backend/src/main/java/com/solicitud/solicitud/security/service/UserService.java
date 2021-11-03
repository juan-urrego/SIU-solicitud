package com.solicitud.solicitud.security.service;

import com.solicitud.solicitud.repository.FileSystemRepository;
import com.solicitud.solicitud.security.dto.NewUserDto;
import com.solicitud.solicitud.security.entity.Role;
import com.solicitud.solicitud.security.entity.User;
import com.solicitud.solicitud.security.enums.RoleName;
import com.solicitud.solicitud.security.repository.UserRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       FileSystemRepository fileSystemRepository,
                       RoleService roleService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.fileSystemRepository = fileSystemRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }
    public Optional<User> getUserByEmail(final String email){
        return userRepository.findByEmail(email);
    }
    public Optional<User> getUserByDirectorActive() {
        Optional<Role> roleDirector = roleService.getByRoleName(RoleName.ROLE_DIRECTOR);
        Set<Role> roles = new HashSet<>();
        roleDirector.ifPresent(roles::add);
        return userRepository.findByActiveAndRolesIn(true, roles);
    }

    public boolean existsByEmail(final String email){
        return userRepository.existsByEmail(email);
    }
    public boolean existsById(final int id){
        return userRepository.existsById(id);
    }

    public boolean existsActiveByEmail(String email) {
        return userRepository.existsByActiveFalseAndEmail(email);
    }


    public List<User> getAll(){
        final List<User> users;
        users = userRepository.findAll();
        return users;
    }

    public String saveImage(byte[] bytes, String name) throws Exception{
        return fileSystemRepository.saveImageFileSystem(bytes, name);
    }

    public FileSystemResource getImageById(int id) {
        Optional<User> user = userRepository.findById(id);
        return fileSystemRepository.findInFileSystem(user.get().getSignatureUrl());
    }

    public FileSystemResource findSignature(String firma) {
        return fileSystemRepository.findInFileSystem(firma);
    }

    public void deleteImageByPath(String location){
        fileSystemRepository.deleteByPath(location);
    }

    public void save(final User user){
        userRepository.save(user);
    }

    public void saveUser(final NewUserDto newUserDto, MultipartFile file) throws Exception {
        User user =
                new User(newUserDto.getName(), newUserDto.getEmail(), newUserDto.getPosition(), true,
                        passwordEncoder.encode(newUserDto.getPassword()), saveImage(file.getBytes(),file.getOriginalFilename()));
        user.setRoles(getRoles(newUserDto));
        save(user);
    }

    public void update(NewUserDto newUserDto, MultipartFile file, int id) throws  Exception {
        User user = getUserById(id).orElseThrow(() -> new Exception("user not found"));
        user.setName(newUserDto.getName());
        user.setEmail(newUserDto.getEmail());
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
        user.setPosition(newUserDto.getPosition());

        if(!getImageById(id).exists() && file != null) {
            user.setSignatureUrl(saveImage(file.getBytes(),file.getOriginalFilename()));
        }
        if(file != null && getImageById(id).exists()){
            deleteImageByPath(user.getSignatureUrl());
            user.setSignatureUrl(saveImage(file.getBytes(),file.getOriginalFilename()));
        }
        user.setRoles(getRoles(newUserDto));
        save(user);
    }

    private Set<Role> getRoles(NewUserDto newUserDto) {
        Optional<Role> roleUser = roleService.getByRoleName(RoleName.ROLE_USER);
        Optional<Role> roleAdmin = roleService.getByRoleName(RoleName.ROLE_ADMIN);
        Optional<Role> roleDirector = roleService.getByRoleName(RoleName.ROLE_DIRECTOR);
        Set<Role> roles = new HashSet<>();
        if(newUserDto.getRoles().contains("admin")) {
            roleAdmin.ifPresent(roles::add);
        }
        else if (newUserDto.getRoles().contains("director")) {
            roleDirector.ifPresent(roles::add);
            Optional<User> userDirector = getUserByDirectorActive();
            if (userDirector.isPresent()){
                userDirector.get().setActive(false);
                save(userDirector.get());
            }
        }
        else {
            roleUser.ifPresent(roles::add);
        }
        return(roles);
    }

    public void delete(int id){
        userRepository.deleteById(id);
    }



}
