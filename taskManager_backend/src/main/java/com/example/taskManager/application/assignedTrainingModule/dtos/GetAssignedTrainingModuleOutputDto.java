package com.example.taskManager.application.assignedTrainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAssignedTrainingModuleOutputDto {
    private final Integer id;
    private final Integer trainingModuleId;
    private final boolean completed;
    private final String trainingModuleName;
    private final String trainingModuleDescription;

}