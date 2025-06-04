package com.example.taskManager.infrastructure.assignedTrainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllAssignedTrainingModuleResponseDto {
    private final Integer id;
    private final Integer userId;
    private final Integer trainingModuleId;
    private final boolean completed;
    
}