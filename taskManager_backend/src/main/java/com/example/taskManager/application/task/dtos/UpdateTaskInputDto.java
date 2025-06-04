package com.example.taskManager.application.task.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateTaskInputDto {
    private final String title;
    private final String description;
    private final Integer trainingModuleId;
    
}