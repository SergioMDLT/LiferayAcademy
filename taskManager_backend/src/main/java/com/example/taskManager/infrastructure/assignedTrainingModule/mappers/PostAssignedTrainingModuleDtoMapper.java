package com.example.taskManager.infrastructure.assignedTrainingModule.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.application.assignedTrainingModule.dtos.CreateAssignedTrainingModuleInputDto;
import com.example.taskManager.application.assignedTrainingModule.dtos.CreateAssignedTrainingModuleOutputDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.CreateAssignedTrainingModuleAdminRequestDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.CreateAssignedTrainingModuleRequestDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.CreateAssignedTrainingModuleResponseDto;

@Component
public class PostAssignedTrainingModuleDtoMapper {

    public CreateAssignedTrainingModuleInputDto toInput(CreateAssignedTrainingModuleRequestDto request, Integer userId) {
        return new CreateAssignedTrainingModuleInputDto(userId, request.getTrainingModuleId());
    }

    public CreateAssignedTrainingModuleInputDto toInput(CreateAssignedTrainingModuleAdminRequestDto request) {
        return new CreateAssignedTrainingModuleInputDto(request.getUserId(), request.getTrainingModuleId());
    }

    public CreateAssignedTrainingModuleResponseDto toResponse(CreateAssignedTrainingModuleOutputDto output) {
        return new CreateAssignedTrainingModuleResponseDto(
            output.getId(),
            output.getTrainingModuleId(),
            output.getCompleted()
        );
    }

}