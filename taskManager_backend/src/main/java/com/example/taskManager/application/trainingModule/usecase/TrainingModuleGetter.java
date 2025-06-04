package com.example.taskManager.application.trainingModule.usecase;

import com.example.taskManager.application.trainingModule.dtos.GetTrainingModuleOutputDto;
import com.example.taskManager.application.trainingModule.mappers.GetTrainingModuleApplicationMapper;
import com.example.taskManager.domain.trainingModule.interfaces.TrainingModuleRepositoryPort;
import com.example.taskManager.domain.trainingModule.models.TrainingModule;
import org.springframework.stereotype.Service;

@Service
public class TrainingModuleGetter {

    private final TrainingModuleRepositoryPort repository;
    private final GetTrainingModuleApplicationMapper mapper;

    public TrainingModuleGetter(
        TrainingModuleRepositoryPort repository,
        GetTrainingModuleApplicationMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public GetTrainingModuleOutputDto execute(Integer id) {
        TrainingModule module = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Training module not found with id: " + id));
        return mapper.toOutput(module);
    }

}