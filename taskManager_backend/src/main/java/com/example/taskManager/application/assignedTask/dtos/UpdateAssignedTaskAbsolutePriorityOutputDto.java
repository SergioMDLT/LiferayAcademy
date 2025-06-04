package com.example.taskManager.application.assignedTask.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateAssignedTaskAbsolutePriorityOutputDto {
    private final Integer assignedTaskId;
    private final String title;
    private final String description;
    private final Boolean completed;
    private final Integer absolutePriority;
    
}