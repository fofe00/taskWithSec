package com.example.taskWithSecWeb.controller;

import com.example.taskWithSecWeb.entities.Task;
import com.example.taskWithSecWeb.entities.User;
import com.example.taskWithSecWeb.repositories.RoleRepository;
import com.example.taskWithSecWeb.repositories.TaskRepository;
import com.example.taskWithSecWeb.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TaskRepository taskRepository;

    public TaskController(UserRepository userRepository, RoleRepository roleRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping({"/alluser","/users"})
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @GetMapping({"","/"})
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }
    @PostMapping({"","/"})
    public Task saveTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

}
