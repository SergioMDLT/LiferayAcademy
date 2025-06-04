package com.example.taskManager.infrastructure.trainingModule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PutTrainingModuleRequestDto {
    private final String name;
    private final String description;
    
}