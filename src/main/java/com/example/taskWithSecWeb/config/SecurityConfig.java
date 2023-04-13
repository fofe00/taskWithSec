package com.example.taskWithSecWeb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf->csrf.ignoringRequestMatchers("/**"))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/tasks").permitAll()
                        .anyRequest().authenticated()
                )
                .headers(header->header.frameOptions().sameOrigin())
                .build();
    }
}
