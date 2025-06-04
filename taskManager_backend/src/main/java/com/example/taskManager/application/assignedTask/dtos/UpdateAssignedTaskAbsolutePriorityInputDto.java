package com.example.taskManager.application.assignedTask.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateAssignedTaskAbsolutePriorityInputDto {
    private final Integer assignedTaskId;
    private final Integer userId;
    private final Integer newPriority;
    
}