package com.example.taskManager.domain.assignedTask.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignedTask {
    private Integer id;
    private Integer userId;
    private Integer taskId;
    private Boolean completed;
    private Integer modulePriority;
    private Integer absolutePriority;

}