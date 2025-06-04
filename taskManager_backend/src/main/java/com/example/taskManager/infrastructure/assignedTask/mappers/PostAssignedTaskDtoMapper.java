package com.example.taskManager.infrastructure.assignedTask.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.application.assignedTask.dtos.CreateAssignedTaskInputDto;
import com.example.taskManager.application.assignedTask.dtos.CreateAssignedTaskOutputDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.CreateAssignedTaskAdminRequestDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.CreateAssignedTaskRequestDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.CreateAssignedTaskResponseDto;

@Component
public class PostAssignedTaskDtoMapper {

    public CreateAssignedTaskInputDto toInput(CreateAssignedTaskRequestDto dto, Integer userId) {
        return new CreateAssignedTaskInputDto(dto.getTaskId(), userId);
    }

    public CreateAssignedTaskInputDto toInput(CreateAssignedTaskAdminRequestDto dto) {
        return new CreateAssignedTaskInputDto(dto.getTaskId(), dto.getUserId());
    }

    public CreateAssignedTaskResponseDto toResponse(CreateAssignedTaskOutputDto dto) {
        return new CreateAssignedTaskResponseDto(
            dto.getId(),
            dto.getTaskId(),
            dto.getCompleted(),
            dto.getModulePriority(),
            dto.getAbsolutePriority()
        );
    }
    
}