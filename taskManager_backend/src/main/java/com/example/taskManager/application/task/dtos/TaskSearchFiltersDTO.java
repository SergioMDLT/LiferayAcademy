package com.example.taskManager.application.task.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskSearchFiltersDTO {
    private String  title;
    private Integer trainingModuleId;
    private int     page;
    private int     size;
    private String  sort;

}