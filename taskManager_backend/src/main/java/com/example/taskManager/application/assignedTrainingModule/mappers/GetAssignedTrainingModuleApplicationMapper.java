package com.example.taskManager.application.assignedTrainingModule.mappers;

import com.example.taskManager.application.assignedTrainingModule.dtos.GetAllAssignedTrainingModuleOutputDto;
import com.example.taskManager.application.assignedTrainingModule.dtos.GetAssignedTrainingModuleOutputDto;
import com.example.taskManager.domain.assignedTrainingModule.models.AssignedTrainingModule;
import com.example.taskManager.domain.trainingModule.models.TrainingModule;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetAssignedTrainingModuleApplicationMapper {

    public GetAssignedTrainingModuleOutputDto toOutput(AssignedTrainingModule assigned, TrainingModule module) {
        return new GetAssignedTrainingModuleOutputDto(
            assigned.getId(),
            assigned.getTrainingModuleId(),
            assigned.isCompleted(),
            module.getName(),
            module.getDescription()
        );
    }

    public List<GetAllAssignedTrainingModuleOutputDto> toOutputList(List<AssignedTrainingModule> modules) {
        return modules.stream()
                .map(m -> new GetAllAssignedTrainingModuleOutputDto(
                        m.getId(),
                        m.getUserId(),
                        m.getTrainingModuleId(),
                        m.isCompleted()
                ))
                .toList();
    }

}