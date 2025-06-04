package com.example.taskManager.infrastructure.assignedTrainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PutAssignedTrainingModuleCompletionRequestDto {
    private final Boolean completed;
    
}