package com.example.taskManager.application.trainingModule.mappers;

import com.example.taskManager.application.trainingModule.dtos.UpdateTrainingModuleInputDto;
import com.example.taskManager.application.trainingModule.dtos.UpdateTrainingModuleOutputDto;
import com.example.taskManager.domain.trainingModule.models.TrainingModule;
import org.springframework.stereotype.Component;

@Component
public class UpdateTrainingModuleApplicationMapper {

    public TrainingModule toDomain(UpdateTrainingModuleInputDto dto) {
        return new TrainingModule(dto.getId(), dto.getName(), dto.getDescription(), null);
    }

    public UpdateTrainingModuleOutputDto toDto(TrainingModule module) {
        return new UpdateTrainingModuleOutputDto(module.getId(), module.getName(), module.getDescription());
    }
    
}