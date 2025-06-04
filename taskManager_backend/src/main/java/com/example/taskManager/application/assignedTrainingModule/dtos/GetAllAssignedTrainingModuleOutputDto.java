package com.example.taskManager.application.assignedTrainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllAssignedTrainingModuleOutputDto {
    private final Integer id;
    private final Integer userId;
    private final Integer trainingModuleId;
    private final boolean completed;
    
}