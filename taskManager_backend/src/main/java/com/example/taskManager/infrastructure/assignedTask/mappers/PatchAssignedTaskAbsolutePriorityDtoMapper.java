package com.example.taskManager.infrastructure.assignedTask.mappers;

import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskAbsolutePriorityInputDto;
import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskAbsolutePriorityOutputDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.PatchAssignedTaskAbsolutePriorityRequestDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.PatchAssignedTaskAbsolutePriorityResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PatchAssignedTaskAbsolutePriorityDtoMapper {

    public UpdateAssignedTaskAbsolutePriorityInputDto toInput(Integer id, Integer userId, PatchAssignedTaskAbsolutePriorityRequestDto request) {
        return new UpdateAssignedTaskAbsolutePriorityInputDto(id, userId, request.getAbsolutePriority());
    }

    public PatchAssignedTaskAbsolutePriorityResponseDto toResponse(UpdateAssignedTaskAbsolutePriorityOutputDto output) {
        return new PatchAssignedTaskAbsolutePriorityResponseDto(
            output.getAssignedTaskId(),
            output.getTitle(),
            output.getDescription(),
            output.getCompleted(),
            output.getAbsolutePriority()
        );
    }
    
}