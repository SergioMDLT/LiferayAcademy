package com.example.taskManager.infrastructure.task.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetTaskResponseDto {
    private final Integer id;
    private final String title;
    private final String description;
    private final Integer trainingModuleId;

}