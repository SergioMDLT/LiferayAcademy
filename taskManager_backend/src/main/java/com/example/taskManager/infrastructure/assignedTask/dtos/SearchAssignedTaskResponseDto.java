package com.example.taskManager.infrastructure.assignedTask.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchAssignedTaskResponseDto {
    private final Integer id;
    private final String title;
    private final String description;
    private final Boolean completed;
    private final Integer modulePriority;
    private final Integer absolutePriority;
    private final Integer trainingModuleId;
    private final Integer userId;
    private final Integer taskId;

}