package com.example.taskManager.application.assignedTrainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateAssignedTrainingModuleCompletionOutputDto {
    private final Integer id;
    private final Boolean completed;
    
}