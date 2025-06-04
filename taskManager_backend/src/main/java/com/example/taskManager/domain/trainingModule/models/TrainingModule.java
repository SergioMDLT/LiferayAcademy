package com.example.taskManager.domain.trainingModule.models;

import java.util.List;
import com.example.taskManager.domain.task.models.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingModule {
    private Integer     id;
    private String      name;
    private String      description;
    private List<Task>  tasks;

}