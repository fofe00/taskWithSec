package com.example.taskWithSecWeb.services;

import com.example.taskWithSecWeb.controller.AuthResponse;
import com.example.taskWithSecWeb.controller.LoginRequest;
import com.example.taskWithSecWeb.controller.RegisterRequest;
import com.example.taskWithSecWeb.entities.User;
import com.example.taskWithSecWeb.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtSerice jwtSerice;
    private final JpaUserDetailservice jpaUserDetailservice;
    private final AuthenticationProvider authenticationProvider;

    public AuthServices(PasswordEncoder passwordEncoder,UserRepository userRepository,JwtSerice jwtSerice,JpaUserDetailservice jpaUserDetailservice,AuthenticationProvider authenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository=userRepository;
        this.jwtSerice=jwtSerice;
        this.jpaUserDetailservice =jpaUserDetailservice;
        this.authenticationProvider =authenticationProvider;
    }

    public AuthResponse register(RegisterRequest request) {
        User user= User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPwd()))
                .build();
        userRepository.save(user);
        //UserDetails userDetails=
        String token=jwtSerice.generateToken(jpaUserDetailservice.loadUserByUsername(user.getName()));
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationProvider.authenticate(
          new UsernamePasswordAuthenticationToken(
                  request.getName(),
                  request.getPwd()
          )
        );
        var user=userRepository.findByName(request.getName()).orElseThrow();
        String token=jwtSerice.generateToken(jpaUserDetailservice.loadUserByUsername(user.getName()));
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
