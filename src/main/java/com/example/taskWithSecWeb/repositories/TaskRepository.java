package com.example.taskWithSecWeb.repositories;

import com.example.taskWithSecWeb.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
