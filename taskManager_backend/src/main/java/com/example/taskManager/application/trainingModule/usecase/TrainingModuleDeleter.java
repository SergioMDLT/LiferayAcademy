package com.example.taskManager.application.trainingModule.usecase;

import com.example.taskManager.application.trainingModule.dtos.DeleteTrainingModuleInputDto;
import com.example.taskManager.domain.trainingModule.interfaces.TrainingModuleRepositoryPort;
import com.example.taskManager.domain.trainingModule.models.TrainingModule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrainingModuleDeleter {

    private final TrainingModuleRepositoryPort repository;

    public TrainingModuleDeleter(TrainingModuleRepositoryPort repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(DeleteTrainingModuleInputDto input) {
        TrainingModule toDelete = new TrainingModule(input.getTrainingModuleId(), null, null, null);
        repository.delete(toDelete);
    }
    
}