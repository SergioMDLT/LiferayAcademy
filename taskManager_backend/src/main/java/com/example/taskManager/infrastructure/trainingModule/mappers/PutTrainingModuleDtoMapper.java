package com.example.taskManager.infrastructure.trainingModule.mappers;

import com.example.taskManager.application.trainingModule.dtos.UpdateTrainingModuleInputDto;
import com.example.taskManager.application.trainingModule.dtos.UpdateTrainingModuleOutputDto;
import com.example.taskManager.infrastructure.trainingModule.dtos.PutTrainingModuleRequestDto;
import com.example.taskManager.infrastructure.trainingModule.dtos.PutTrainingModuleResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PutTrainingModuleDtoMapper {

    public UpdateTrainingModuleInputDto toInput(Integer id, PutTrainingModuleRequestDto dto) {
        return new UpdateTrainingModuleInputDto(id, dto.getName(), dto.getDescription());
    }

    public PutTrainingModuleResponseDto toResponse(UpdateTrainingModuleOutputDto dto) {
        return new PutTrainingModuleResponseDto(dto.getId(), dto.getName(), dto.getDescription());
    }
    
}