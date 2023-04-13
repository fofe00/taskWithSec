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

@SpringBootApplication
public class TaskWithSecWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskWithSecWebApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(TaskRepository taskRepository, UserRepository userRepository, RoleRepository roleRepository){
		return args -> {
			/*taskRepository.save(new Task(null,"task one"));
			taskRepository.save(new Task(null,"task two"));
			taskRepository.save(new Task(null,"task three"));*/
			/*userRepository.save(new User(null,"admin","admin@gmail.com","pwd",null));
			userRepository.save(new User(null,"user","user@gmail.com","pwd",null));*/
			User user=userRepository.findByName("user").get();
			User admin=userRepository.findByName("admin").get();
			//Role roleAdmin =roleRepository.save(new Role(null,"ADMIN"));
			Role roleAdmin =roleRepository.findByName("ADMIN").get();
			Role roleUser =roleRepository.findByName("USER").get();
			//Role roleUser =roleRepository.save(new Role(null,"USER"));
			user.addRole(roleUser);
			userRepository.save(user);
			admin.addRole(roleAdmin);
			userRepository.save(admin);
		};
	}
}
