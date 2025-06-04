package com.example.taskManager.application.assignedTrainingModule.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.application.assignedTrainingModule.dtos.CreateAssignedTrainingModuleInputDto;
import com.example.taskManager.application.assignedTrainingModule.dtos.CreateAssignedTrainingModuleOutputDto;
import com.example.taskManager.domain.assignedTrainingModule.models.AssignedTrainingModule;

@Component
public class CreateAssignedTrainingModuleApplicationMapper {

    public AssignedTrainingModule toDomain(CreateAssignedTrainingModuleInputDto dto) {
        return new AssignedTrainingModule(
            null,
            dto.getUserId(),
            dto.getTrainingModuleId(),
            false
        );
    }

    public CreateAssignedTrainingModuleOutputDto toDTO(AssignedTrainingModule domain) {
        return new CreateAssignedTrainingModuleOutputDto(
            domain.getId(),
            domain.getTrainingModuleId(),
            domain.isCompleted()
        );
    }

}