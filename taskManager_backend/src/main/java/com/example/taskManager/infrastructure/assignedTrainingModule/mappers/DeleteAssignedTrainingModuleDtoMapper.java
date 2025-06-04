package com.example.taskManager.infrastructure.assignedTrainingModule.mappers;

import com.example.taskManager.application.assignedTrainingModule.dtos.DeleteAssignedTrainingModuleInputDto;
import org.springframework.stereotype.Component;

@Component
public class DeleteAssignedTrainingModuleDtoMapper {

    public DeleteAssignedTrainingModuleInputDto toInput(Integer userId, Integer assignedTrainingModuleId) {
        return new DeleteAssignedTrainingModuleInputDto(userId, assignedTrainingModuleId);
    }
    
}