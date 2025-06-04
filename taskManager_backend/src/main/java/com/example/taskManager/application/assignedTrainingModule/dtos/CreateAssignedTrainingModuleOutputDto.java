package com.example.taskManager.application.assignedTrainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAssignedTrainingModuleOutputDto {
    private final Integer id;
    private final Integer trainingModuleId;
    private final Boolean completed;
    
}