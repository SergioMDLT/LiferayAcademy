package com.example.taskManager.application.assignedTrainingModule.mappers;

import com.example.taskManager.application.assignedTrainingModule.dtos.UpdateAssignedTrainingModuleCompletionInputDto;
import com.example.taskManager.application.assignedTrainingModule.dtos.UpdateAssignedTrainingModuleCompletionOutputDto;
import com.example.taskManager.domain.assignedTrainingModule.models.AssignedTrainingModule;
import org.springframework.stereotype.Component;

@Component
public class UpdateAssignedTrainingModuleCompletionApplicationMapper {

    public AssignedTrainingModule toDomain(UpdateAssignedTrainingModuleCompletionInputDto dto) {
        return new AssignedTrainingModule(
            dto.getAssignedTrainingModuleId(),
            dto.getUserId(),
            null,
            dto.getCompleted()
        );
    }

    public UpdateAssignedTrainingModuleCompletionOutputDto toDto(AssignedTrainingModule domain) {
        return new UpdateAssignedTrainingModuleCompletionOutputDto(
            domain.getId(),
            domain.isCompleted()
        );
    }
    
}