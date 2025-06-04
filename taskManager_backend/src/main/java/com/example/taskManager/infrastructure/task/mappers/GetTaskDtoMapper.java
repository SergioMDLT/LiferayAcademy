package com.example.taskManager.infrastructure.task.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.application.task.dtos.GetTaskOutputDto;
import com.example.taskManager.infrastructure.task.dtos.GetTaskResponseDto;

@Component
public class GetTaskDtoMapper {

    public GetTaskResponseDto toResponse(GetTaskOutputDto output) {
        return new GetTaskResponseDto(
            output.getId(),
            output.getTitle(),
            output.getDescription(),
            output.getTrainingModuleId()
        );
    }
    
}