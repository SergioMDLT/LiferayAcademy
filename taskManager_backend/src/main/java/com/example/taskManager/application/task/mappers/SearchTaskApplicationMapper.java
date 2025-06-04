package com.example.taskManager.application.task.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.application.task.dtos.SearchTaskOutputDto;
import com.example.taskManager.domain.task.models.Task;

@Component
public class SearchTaskApplicationMapper {

    public SearchTaskOutputDto toDto(Task task) {
        return new SearchTaskOutputDto(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getTrainingModuleId()
        );
    }

}