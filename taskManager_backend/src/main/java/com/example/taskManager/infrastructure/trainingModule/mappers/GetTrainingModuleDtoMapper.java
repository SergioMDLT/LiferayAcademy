package com.example.taskManager.infrastructure.trainingModule.mappers;

import com.example.taskManager.application.trainingModule.dtos.GetTrainingModuleOutputDto;
import com.example.taskManager.infrastructure.trainingModule.dtos.GetTrainingModuleResponseDto;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetTrainingModuleDtoMapper {

    public GetTrainingModuleResponseDto toResponse(GetTrainingModuleOutputDto dto) {
        return new GetTrainingModuleResponseDto(
            dto.getId(),
            dto.getName(),
            dto.getDescription()
        );
    }

    public List<GetTrainingModuleResponseDto> toResponseList(List<GetTrainingModuleOutputDto> outputs) {
        return outputs.stream()
            .map(dto -> new GetTrainingModuleResponseDto(
                    dto.getId(),
                    dto.getName(),
                    dto.getDescription()
            ))
            .toList();
    }

}