package com.example.taskManager.infrastructure.assignedTask.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.taskManager.application.assignedTask.usecase.AssignedTaskDeleter;
import com.example.taskManager.infrastructure.auth.services.AuthenticatedUserProvider;

@RestController
@RequestMapping("/assigned-tasks")
@CrossOrigin("http://localhost:4200")
public class AssignedTaskDeleteController {

    private final AssignedTaskDeleter assignedTaskDeleter;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public AssignedTaskDeleteController(
        AssignedTaskDeleter assignedTaskDeleter,
        AuthenticatedUserProvider authenticatedUserProvider
    ) {
        this.assignedTaskDeleter = assignedTaskDeleter;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignedTask(@PathVariable Integer id) {
        Integer userId = authenticatedUserProvider.execute(true).getUserId();
        assignedTaskDeleter.execute(id, userId);
        return ResponseEntity.noContent().build();
    }

}