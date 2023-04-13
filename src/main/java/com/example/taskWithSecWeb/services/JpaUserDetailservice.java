package com.example.taskWithSecWeb.services;

import com.example.taskWithSecWeb.entities.SecurityUser;
import com.example.taskWithSecWeb.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailservice implements UserDetailsService {
    private final UserRepository userRepository;

    public JpaUserDetailservice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username).map(SecurityUser::new).orElseThrow(()-> new UsernameNotFoundException("User Name not found "+ username));
    }
}
