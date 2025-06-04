package com.example.taskManager.infrastructure.assignedTrainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAssignedTrainingModuleAdminRequestDto {
    private final Integer userId;
    private final Integer trainingModuleId;

}