package com.example.taskManager.infrastructure.assignedTrainingModule.mappers;

import com.example.taskManager.application.assignedTrainingModule.dtos.UpdateAssignedTrainingModuleCompletionInputDto;
import com.example.taskManager.application.assignedTrainingModule.dtos.UpdateAssignedTrainingModuleCompletionOutputDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.PutAssignedTrainingModuleCompletionRequestDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.PutAssignedTrainingModuleCompletionResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PutAssignedTrainingModuleCompletionDtoMapper {

    public UpdateAssignedTrainingModuleCompletionInputDto toInput(Integer userId, Integer assignedId, PutAssignedTrainingModuleCompletionRequestDto request) {
        return new UpdateAssignedTrainingModuleCompletionInputDto(
            userId,
            assignedId,
            request.getCompleted()
        );
    }

    public PutAssignedTrainingModuleCompletionResponseDto toResponse(UpdateAssignedTrainingModuleCompletionOutputDto output) {
        return new PutAssignedTrainingModuleCompletionResponseDto(
            output.getId(),
            output.getCompleted()
        );
    }
    
}