package com.example.taskWithSecWeb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping({"","/"})
    public String all(){
        return "hello All";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/admin"})
    public String admin(){
        return "hello Admin";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping({"/user"})
    public String user(){
        return "hello User";
    }
}
