package com.example.taskManager.infrastructure.assignedTrainingModule.mappers;

import com.example.taskManager.application.assignedTrainingModule.dtos.GetAllAssignedTrainingModuleOutputDto;
import com.example.taskManager.application.assignedTrainingModule.dtos.GetAssignedTrainingModuleOutputDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.GetAllAssignedTrainingModuleResponseDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.GetAssignedTrainingModuleResponseDto;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetAssignedTrainingModuleDtoMapper {

    public List<GetAssignedTrainingModuleResponseDto> toResponseList(List<GetAssignedTrainingModuleOutputDto> outputList) {
        return outputList.stream()
                .map(o -> new GetAssignedTrainingModuleResponseDto(
                        o.getId(),
                        o.getTrainingModuleId(),
                        o.isCompleted(),
                        o.getTrainingModuleName(),
                        o.getTrainingModuleDescription()
                ))
                .toList();
    }

    public List<GetAllAssignedTrainingModuleResponseDto> toResponseListAll(List<GetAllAssignedTrainingModuleOutputDto> outputList) {
        return outputList.stream()
                .map(o -> new GetAllAssignedTrainingModuleResponseDto(
                        o.getId(),
                        o.getUserId(),
                        o.getTrainingModuleId(),
                        o.isCompleted()
                ))
                .toList();
    }

}