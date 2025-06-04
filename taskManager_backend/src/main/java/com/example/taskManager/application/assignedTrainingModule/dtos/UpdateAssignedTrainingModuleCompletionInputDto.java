package com.example.taskManager.application.assignedTrainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateAssignedTrainingModuleCompletionInputDto {
    private final Integer userId;
    private final Integer assignedTrainingModuleId;
    private final Boolean completed;
    
}