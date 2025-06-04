package com.example.taskManager.infrastructure.assignedTask.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAssignedTaskResponseDto {
    private final Integer id;
    private final Integer taskId;
    private final Boolean completed;
    private final Integer modulePriority;
    private final Integer absolutePriority;

}