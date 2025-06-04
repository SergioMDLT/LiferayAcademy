package com.example.taskManager.infrastructure.task.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.application.task.dtos.UpdateTaskInputDto;
import com.example.taskManager.infrastructure.task.dtos.UpdateTaskRequestDto;

@Component
public class PatchTaskDtoMapper {

    public UpdateTaskInputDto toInput(UpdateTaskRequestDto dto) {
        return new UpdateTaskInputDto(dto.getTitle(), dto.getDescription(), dto.getTrainingModuleId());
    }

}