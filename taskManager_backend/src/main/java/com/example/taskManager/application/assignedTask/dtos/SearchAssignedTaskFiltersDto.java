package com.example.taskManager.application.assignedTask.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchAssignedTaskFiltersDto {
    private final Integer userId;
    private final String title;
    private final Boolean completed;
    private final Integer trainingModuleId;
    private final int page;
    private final int size;
    private final String sort;

}