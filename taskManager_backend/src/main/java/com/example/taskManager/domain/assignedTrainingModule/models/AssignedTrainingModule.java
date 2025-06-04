package com.example.taskManager.domain.assignedTrainingModule.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignedTrainingModule {
    private Integer id;
    private Integer userId;
    private Integer trainingModuleId;
    private boolean completed;

}