package com.example.taskManager.infrastructure.assignedTrainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PutAssignedTrainingModuleCompletionResponseDto {
    private final Integer id;
    private final Boolean completed;
    
}