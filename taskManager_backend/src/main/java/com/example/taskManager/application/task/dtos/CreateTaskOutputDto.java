package com.example.taskManager.application.task.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskOutputDto {
    private Integer id;
    private String  title;
    private String  description;
    private Integer trainingModuleId;

}