package com.example.taskManager.application.trainingModule.mappers;

import com.example.taskManager.application.trainingModule.dtos.GetTrainingModuleOutputDto;
import com.example.taskManager.domain.trainingModule.models.TrainingModule;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetTrainingModuleApplicationMapper {

    public GetTrainingModuleOutputDto toOutput(TrainingModule module) {
        return new GetTrainingModuleOutputDto(
            module.getId(),
            module.getName(),
            module.getDescription()
        );
    }

    public List<GetTrainingModuleOutputDto> toOutputList(List<TrainingModule> modules) {
        return modules.stream()
                .map(m -> new GetTrainingModuleOutputDto(
                        m.getId(),
                        m.getName(),
                        m.getDescription()
                ))
                .toList();
    }

}