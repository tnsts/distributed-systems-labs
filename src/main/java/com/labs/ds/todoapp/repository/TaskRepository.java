package com.labs.ds.todoapp.repository;

import com.labs.ds.todoapp.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
