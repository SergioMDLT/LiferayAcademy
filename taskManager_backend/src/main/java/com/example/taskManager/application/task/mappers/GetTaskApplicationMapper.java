package com.example.taskManager.application.task.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.application.task.dtos.GetTaskOutputDto;
import com.example.taskManager.domain.task.models.Task;

@Component
public class GetTaskApplicationMapper {

    public GetTaskOutputDto toDTO(Task task) {
        return new GetTaskOutputDto(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getTrainingModuleId()
        );
    }

}