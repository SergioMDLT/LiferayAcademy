package com.example.taskManager.infrastructure.assignedTask.mappers;

import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskModulePriorityInputDto;
import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskModulePriorityOutputDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.PatchAssignedTaskModulePriorityRequestDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.PatchAssignedTaskModulePriorityResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PatchAssignedTaskModulePriorityDtoMapper {

    public UpdateAssignedTaskModulePriorityInputDto toInput(Integer id, Integer userId, PatchAssignedTaskModulePriorityRequestDto request) {
        return new UpdateAssignedTaskModulePriorityInputDto(id, userId, request.getModulePriority());
    }

    public PatchAssignedTaskModulePriorityResponseDto toResponse(UpdateAssignedTaskModulePriorityOutputDto output) {
        return new PatchAssignedTaskModulePriorityResponseDto(
            output.getAssignedTaskId(),
            output.getTitle(),
            output.getDescription(),
            output.getCompleted(),
            output.getModulePriority()
        );
    }

}