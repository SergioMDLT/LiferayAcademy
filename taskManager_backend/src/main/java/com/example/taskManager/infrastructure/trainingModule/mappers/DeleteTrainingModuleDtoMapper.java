package com.example.taskManager.infrastructure.trainingModule.mappers;

import com.example.taskManager.application.trainingModule.dtos.DeleteTrainingModuleInputDto;
import org.springframework.stereotype.Component;

@Component
public class DeleteTrainingModuleDtoMapper {

    public DeleteTrainingModuleInputDto toInput(Integer id) {
        return new DeleteTrainingModuleInputDto(id);
    }
    
}