package com.example.taskManager.domain.trainingModule.interfaces;

import java.util.List;
import java.util.Optional;
import com.example.taskManager.domain.trainingModule.models.TrainingModule;

public interface TrainingModuleRepositoryPort {

    Optional<TrainingModule> findById(Integer id);

    TrainingModule save(TrainingModule trainingModule);

    void delete(TrainingModule trainingModule);

    List<TrainingModule> findDefaultModules();

}