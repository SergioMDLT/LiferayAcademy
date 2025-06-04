package com.example.taskManager.domain.assignedTask.exceptions;

public class AssignedTaskNotFoundException extends RuntimeException {
    public AssignedTaskNotFoundException(String message) {
        super(message);
    }
    
}