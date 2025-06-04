package com.example.taskManager.infrastructure.assignedTrainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAssignedTrainingModuleResponseDto {
    private final Integer id;
    private final Integer trainingModuleId;
    private final boolean completed;
    private final String name;
    private final String description;
    
}