package com.example.taskManager.infrastructure.assignedTask.mappers;

import com.example.taskManager.application.assignedTask.dtos.SearchAssignedTaskOutputDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.SearchAssignedTaskResponseDto;
import org.springframework.stereotype.Component;

@Component
public class SearchAssignedTaskDtoMapper {

    public SearchAssignedTaskResponseDto toResponse(SearchAssignedTaskOutputDto dto) {
        return new SearchAssignedTaskResponseDto(
            dto.getId(),
            dto.getTitle(),
            dto.getDescription(),
            dto.getCompleted(),
            dto.getModulePriority(),
            dto.getAbsolutePriority(),
            dto.getTrainingModuleId(),
            dto.getUserId(),
            dto.getTaskId()
        );
    }

}