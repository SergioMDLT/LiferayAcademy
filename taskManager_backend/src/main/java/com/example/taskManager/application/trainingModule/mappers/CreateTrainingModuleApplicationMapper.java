package com.example.taskManager.application.trainingModule.mappers;

import com.example.taskManager.application.trainingModule.dtos.CreateTrainingModuleInputDto;
import com.example.taskManager.application.trainingModule.dtos.CreateTrainingModuleOutputDto;
import com.example.taskManager.domain.trainingModule.models.TrainingModule;
import org.springframework.stereotype.Component;

@Component
public class CreateTrainingModuleApplicationMapper {

    public TrainingModule toDomain(CreateTrainingModuleInputDto dto) {
        return new TrainingModule(null, dto.getName(), dto.getDescription(), null);
    }

    public CreateTrainingModuleOutputDto toDto(TrainingModule domain) {
        return new CreateTrainingModuleOutputDto(
            domain.getId(),
            domain.getName(),
            domain.getDescription()
        );
    }
    
}