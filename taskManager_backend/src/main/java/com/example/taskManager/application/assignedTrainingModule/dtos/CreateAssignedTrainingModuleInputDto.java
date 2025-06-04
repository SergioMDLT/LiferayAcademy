package com.example.taskManager.application.assignedTrainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAssignedTrainingModuleInputDto {
    private final Integer userId;
    private final Integer trainingModuleId;
    
}