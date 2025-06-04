package com.example.taskManager.infrastructure.assignedTask.mappers;

import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskCompletionInputDto;
import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskCompletionOutputDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.PatchAssignedTaskCompletionResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PatchAssignedTaskCompletionDtoMapper {

    public UpdateAssignedTaskCompletionInputDto toInput(Integer id, Integer userId) {
        return new UpdateAssignedTaskCompletionInputDto(id, userId);
    }

    public PatchAssignedTaskCompletionResponseDto toResponse(UpdateAssignedTaskCompletionOutputDto output) {
        return new PatchAssignedTaskCompletionResponseDto(
            output.getAssignedTaskId(),
            output.getTitle(),
            output.getDescription(),
            output.getCompleted(),
            output.getModulePriority(),
            output.getAbsolutePriority()
        );
    }
    
}