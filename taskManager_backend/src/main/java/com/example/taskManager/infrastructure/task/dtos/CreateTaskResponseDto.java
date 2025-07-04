package com.example.taskManager.infrastructure.task.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskResponseDto {
    private Integer id;
    private String  title;
    private String  description;
    private Integer trainingModuleId;

}