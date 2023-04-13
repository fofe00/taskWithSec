package com.example.taskWithSecWeb.config;

import com.example.taskWithSecWeb.services.JpaUserDetailservice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final JpaUserDetailservice jpaUserDetailservice;

    public SecurityConfig(JpaUserDetailservice jpaUserDetailservice) {
        this.jpaUserDetailservice = jpaUserDetailservice;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf->csrf.ignoringRequestMatchers("/**"))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/tasks/**").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(jpaUserDetailservice)
                .httpBasic(Customizer.withDefaults())
                .headers(header->header.frameOptions().sameOrigin())
                .build();
    }
}
