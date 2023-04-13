package com.example.taskWithSecWeb;

import com.example.taskWithSecWeb.entities.Role;
import com.example.taskWithSecWeb.entities.Task;
import com.example.taskWithSecWeb.entities.User;
import com.example.taskWithSecWeb.repositories.RoleRepository;
import com.example.taskWithSecWeb.repositories.TaskRepository;
import com.example.taskWithSecWeb.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TaskWithSecWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskWithSecWebApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TaskRepository taskRepository, UserRepository userRepository, RoleRepository roleRepository){
		return args -> {
			//create task
/*			taskRepository.save(new Task(null,"task one"));
			taskRepository.save(new Task(null,"task two"));
			taskRepository.save(new Task(null,"task three"));*/
			// create users
			userRepository.save(new User(null,"admin","admin@gmail.com", passwordEncoder().encode("pwd"), null));
			userRepository.save(new User(null,"user","user@gmail.com", passwordEncoder().encode("pwd"), null));
			User user=userRepository.findByName("user").get();
			User admin=userRepository.findByName("admin").get();
			// create role
			Role roleUser =roleRepository.save(new Role(null,"USER"));
			Role roleAdmin =roleRepository.save(new Role(null,"ADMIN"));
			//add roles to users
			user.getRoles().add(roleUser);
			userRepository.save(user);
			admin.getRoles().add(roleAdmin);
			admin.getRoles().add(roleUser);
			userRepository.save(admin);
		};
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new  BCryptPasswordEncoder();
	}
}
