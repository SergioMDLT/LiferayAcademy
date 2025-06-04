package com.example.taskManager.application.trainingModule.usecase;

import com.example.taskManager.application.trainingModule.dtos.UpdateTrainingModuleInputDto;
import com.example.taskManager.application.trainingModule.dtos.UpdateTrainingModuleOutputDto;
import com.example.taskManager.application.trainingModule.mappers.UpdateTrainingModuleApplicationMapper;
import com.example.taskManager.domain.trainingModule.interfaces.TrainingModuleRepositoryPort;
import com.example.taskManager.domain.trainingModule.models.TrainingModule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrainingModuleUpdater {

    private final TrainingModuleRepositoryPort repository;
    private final UpdateTrainingModuleApplicationMapper mapper;

    public TrainingModuleUpdater(
        TrainingModuleRepositoryPort repository,
        UpdateTrainingModuleApplicationMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public UpdateTrainingModuleOutputDto execute(UpdateTrainingModuleInputDto input) {
        TrainingModule updated = mapper.toDomain(input);
        TrainingModule saved = repository.save(updated); // sobrescribe el existente
        return mapper.toDto(saved);
    }
    
}