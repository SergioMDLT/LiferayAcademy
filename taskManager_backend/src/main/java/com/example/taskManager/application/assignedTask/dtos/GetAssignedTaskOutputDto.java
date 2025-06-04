package com.example.taskManager.application.assignedTask.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAssignedTaskOutputDto {
    private final Integer assignedTaskId;
    private final String title;
    private final String description;
    private final Boolean completed;
    private final Integer modulePriority;
    private final Integer absolutePriority;
    private final Integer trainingModuleId;
    
}