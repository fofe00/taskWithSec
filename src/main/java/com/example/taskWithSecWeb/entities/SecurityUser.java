package com.example.taskWithSecWeb.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityUser implements UserDetails {
    private User user;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles=user.getRoles();
        Collection<SimpleGrantedAuthority> authorityCollection= new ArrayList<>();
        authorityCollection=roles.stream()
                .map(role -> "ROLE_"+role.getName())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        for (Role role :
                roles) {
            authorityCollection.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        return authorityCollection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
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
}
