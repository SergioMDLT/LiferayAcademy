package com.example.taskManager.infrastructure.assignedTrainingModule.mappers;

import com.example.taskManager.domain.assignedTrainingModule.models.AssignedTrainingModule;
import com.example.taskManager.infrastructure.assignedTrainingModule.entities.AssignedTrainingModuleEntity;
import com.example.taskManager.infrastructure.trainingModule.entities.TrainingModuleEntity;
import com.example.taskManager.infrastructure.user.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class AssignedTrainingModuleEntityMapper {

    public AssignedTrainingModule toDomain(AssignedTrainingModuleEntity entity) {
        return new AssignedTrainingModule(
            entity.getId(),
            entity.getUser().getId(),
            entity.getTrainingModule().getId(),
            entity.isCompleted()
        );
    }

    public AssignedTrainingModuleEntity toEntity(
        AssignedTrainingModule domain,
        UserEntity user,
        TrainingModuleEntity module
    ) {
        AssignedTrainingModuleEntity entity = new AssignedTrainingModuleEntity();
        entity.setId(domain.getId());
        entity.setUser(user);
        entity.setTrainingModule(module);
        entity.setCompleted(domain.isCompleted());
        return entity;
    }

}