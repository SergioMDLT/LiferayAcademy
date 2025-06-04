package com.example.taskManager.infrastructure.assignedTask.mappers;

import com.example.taskManager.application.assignedTask.dtos.GetAssignedTaskOutputDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.GetAssignedTaskResponseDto;
import org.springframework.stereotype.Component;

@Component
public class GetAssignedTaskDtoMapper {

    public GetAssignedTaskResponseDto toResponse(GetAssignedTaskOutputDto dto) {
        return new GetAssignedTaskResponseDto(
            dto.getAssignedTaskId(),
            dto.getTitle(),
            dto.getDescription(),
            dto.getCompleted(),
            dto.getModulePriority(),
            dto.getAbsolutePriority(),
            dto.getTrainingModuleId()
        );
    }

}