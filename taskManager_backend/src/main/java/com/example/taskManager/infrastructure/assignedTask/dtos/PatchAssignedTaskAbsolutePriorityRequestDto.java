package com.example.taskManager.infrastructure.assignedTask.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchAssignedTaskAbsolutePriorityRequestDto {
    private final Integer absolutePriority;
    
}