package com.example.taskManager.infrastructure.trainingModule.mappers;

import com.example.taskManager.application.trainingModule.dtos.CreateTrainingModuleInputDto;
import com.example.taskManager.application.trainingModule.dtos.CreateTrainingModuleOutputDto;
import com.example.taskManager.infrastructure.trainingModule.dtos.PostTrainingModuleRequestDto;
import com.example.taskManager.infrastructure.trainingModule.dtos.PostTrainingModuleResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PostTrainingModuleDtoMapper {

    public CreateTrainingModuleInputDto toInput(PostTrainingModuleRequestDto requestDto) {
        return new CreateTrainingModuleInputDto(requestDto.getName(), requestDto.getDescription());
    }

    public PostTrainingModuleResponseDto toResponse(CreateTrainingModuleOutputDto outputDto) {
        return new PostTrainingModuleResponseDto(
            outputDto.getId(),
            outputDto.getName(),
            outputDto.getDescription()
        );
    }
    
}