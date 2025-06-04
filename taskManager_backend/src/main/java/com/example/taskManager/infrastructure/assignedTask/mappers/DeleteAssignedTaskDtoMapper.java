package com.example.taskManager.infrastructure.assignedTask.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.application.assignedTask.dtos.DeleteAssignedTaskInputDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.DeleteAssignedTaskResponseDto;

@Component
public class DeleteAssignedTaskDtoMapper {

    public DeleteAssignedTaskInputDto toInput(Integer id, Integer userId) {
        return new DeleteAssignedTaskInputDto(id, userId);
    }

    public DeleteAssignedTaskResponseDto toResponse(Integer id) {
        return new DeleteAssignedTaskResponseDto(id, "Assigned task deleted successfully");
    }
    
}