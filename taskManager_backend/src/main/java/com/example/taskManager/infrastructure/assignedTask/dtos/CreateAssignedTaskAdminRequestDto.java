package com.example.taskManager.infrastructure.assignedTask.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAssignedTaskAdminRequestDto {
    private final Integer taskId;
    private final Integer userId;

}
