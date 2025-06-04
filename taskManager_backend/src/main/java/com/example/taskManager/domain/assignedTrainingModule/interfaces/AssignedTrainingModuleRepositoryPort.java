package com.example.taskManager.domain.assignedTrainingModule.interfaces;

import java.util.List;
import com.example.taskManager.domain.assignedTrainingModule.models.AssignedTrainingModule;

public interface AssignedTrainingModuleRepositoryPort {

    AssignedTrainingModule save(AssignedTrainingModule assignedTrainingModule);

    void delete(AssignedTrainingModule assignedTrainingModule);

    List<AssignedTrainingModule> findAll();

    List<AssignedTrainingModule> findByUserId(Integer userId);

    boolean existsByUserIdAndTrainingModuleId(Integer userId, Integer trainingModuleId);
}
