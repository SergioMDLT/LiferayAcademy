package com.example.taskManager.application.trainingModule.usecase;

import com.example.taskManager.application.trainingModule.dtos.CreateTrainingModuleInputDto;
import com.example.taskManager.application.trainingModule.dtos.CreateTrainingModuleOutputDto;
import com.example.taskManager.application.trainingModule.mappers.CreateTrainingModuleApplicationMapper;
import com.example.taskManager.domain.trainingModule.interfaces.TrainingModuleRepositoryPort;
import com.example.taskManager.domain.trainingModule.models.TrainingModule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrainingModuleCreator {

    private final TrainingModuleRepositoryPort repository;
    private final CreateTrainingModuleApplicationMapper mapper;

    public TrainingModuleCreator(
        TrainingModuleRepositoryPort repository,
        CreateTrainingModuleApplicationMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public CreateTrainingModuleOutputDto execute(CreateTrainingModuleInputDto input) {
        TrainingModule newModule = mapper.toDomain(input);
        TrainingModule saved = repository.save(newModule);
        return mapper.toDto(saved);
    }
    
}