package com.example.taskManager.application.trainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetTrainingModuleOutputDto {
    private final Integer id;
    private final String name;
    private final String description;
    
}