package com.example.taskManager.infrastructure.assignedTrainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAssignedTrainingModuleResponseDto {
    private final Integer id;
    private final Integer trainingModuleId;
    private final Boolean completed;

}