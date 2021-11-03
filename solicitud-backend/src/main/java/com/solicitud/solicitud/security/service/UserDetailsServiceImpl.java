package com.solicitud.solicitud.security.service;

import com.solicitud.solicitud.security.entity.User;
import com.solicitud.solicitud.security.entity.MainUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    final
    UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email: " + email)
        );
        return MainUser.build(user);
    }
}
