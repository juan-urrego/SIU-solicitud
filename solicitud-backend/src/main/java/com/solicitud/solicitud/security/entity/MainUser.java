package com.solicitud.solicitud.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MainUser implements UserDetails {
    private final String name;
    private final String email;
    private final String position;
    private final boolean active;
    private final String password;
    private final String signatureUrl;
    private final Collection<? extends GrantedAuthority> authorities;

    public MainUser(String name, String email, String position, boolean active, String password, String signatureUrl, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.email = email;
        this.position = position;
        this.active = active;
        this.password = password;
        this.signatureUrl = signatureUrl;
        this.authorities = authorities;
    }

    public static MainUser build(User user){
        List<GrantedAuthority> authorities =
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role
                        .getRoleName().name())).collect(Collectors.toList());
        return new MainUser(user.getName(), user.getEmail(), user.getPosition(), user.isActive(), user.getPassword(), user.getSignatureUrl(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return name;
    }


}
