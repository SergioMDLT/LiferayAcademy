package com.example.taskManager.application.assignedTrainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteAssignedTrainingModuleInputDto {
    private final Integer userId;
    private final Integer assignedTrainingModuleId;
    
}