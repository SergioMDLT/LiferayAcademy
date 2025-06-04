package com.example.taskManager.application.trainingModule.usecase;

import com.example.taskManager.application.trainingModule.dtos.GetTrainingModuleOutputDto;
import com.example.taskManager.application.trainingModule.mappers.GetTrainingModuleApplicationMapper;
import com.example.taskManager.domain.trainingModule.interfaces.TrainingModuleRepositoryPort;
import com.example.taskManager.domain.trainingModule.models.TrainingModule;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrainingModuleLister {

    private final TrainingModuleRepositoryPort repository;
    private final GetTrainingModuleApplicationMapper mapper;

    public TrainingModuleLister(
        TrainingModuleRepositoryPort repository,
        GetTrainingModuleApplicationMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<GetTrainingModuleOutputDto> execute() {
        List<TrainingModule> modules = repository.findDefaultModules();
        return mapper.toOutputList(modules);
    }

}