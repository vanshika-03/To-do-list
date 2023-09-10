package com.todo.tasks.repository;

import com.todo.tasks.entity.Tasks;
import com.todo.tasks.entity.TasksPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, TasksPK> {
    public Tasks findByTaskName(String name);
}
