package com.example.taskManager.application.task.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTaskInputDto {
    private Integer page;
    private Integer size;
    private String  sort;

}