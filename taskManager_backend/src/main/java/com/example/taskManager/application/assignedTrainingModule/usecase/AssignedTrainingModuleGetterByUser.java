package com.example.taskManager.application.assignedTrainingModule.usecase;

import com.example.taskManager.application.assignedTrainingModule.dtos.GetAssignedTrainingModuleOutputDto;
import com.example.taskManager.application.assignedTrainingModule.mappers.GetAssignedTrainingModuleApplicationMapper;
import com.example.taskManager.domain.assignedTrainingModule.interfaces.AssignedTrainingModuleRepositoryPort;
import com.example.taskManager.domain.assignedTrainingModule.models.AssignedTrainingModule;
import com.example.taskManager.domain.trainingModule.interfaces.TrainingModuleRepositoryPort;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AssignedTrainingModuleGetterByUser {

    private final AssignedTrainingModuleRepositoryPort repository;
    private final TrainingModuleRepositoryPort trainingModuleRepository;
    private final GetAssignedTrainingModuleApplicationMapper mapper;

    public AssignedTrainingModuleGetterByUser(
        AssignedTrainingModuleRepositoryPort repository,
        TrainingModuleRepositoryPort trainingModuleRepository,
        GetAssignedTrainingModuleApplicationMapper mapper
    ) {
        this.repository = repository;
        this.trainingModuleRepository = trainingModuleRepository;
        this.mapper = mapper;
    }

    public List<GetAssignedTrainingModuleOutputDto> execute(Integer userId) {
        List<AssignedTrainingModule> assignedModules = repository.findByUserId(userId);

        return assignedModules.stream()
            .map(assigned -> {
                var module = trainingModuleRepository.findById(assigned.getTrainingModuleId())
                    .orElseThrow(() -> new IllegalStateException("Training module not found"));
                return mapper.toOutput(assigned, module);
            })
            .toList();
    }

}