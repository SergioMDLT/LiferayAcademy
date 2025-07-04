package com.example.taskManager.infrastructure.task.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.taskManager.application.task.usecase.TaskDeleter;
import com.example.taskManager.infrastructure.auth.services.AuthenticatedUserProvider;

@RestController
@RequestMapping("/tasks")
@CrossOrigin("http://localhost:4200")
public class TaskDeleteController {

    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final TaskDeleter               taskDeleter;

    public TaskDeleteController(
        AuthenticatedUserProvider   authenticatedUserProvider,
        TaskDeleter                 taskDeleter
    ) {
        this.authenticatedUserProvider =    authenticatedUserProvider;
        this.taskDeleter =                  taskDeleter;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        Integer userId = authenticatedUserProvider.execute(true).getUserId();
        taskDeleter.execute(id, userId);
        return ResponseEntity.noContent().build();
    }

}