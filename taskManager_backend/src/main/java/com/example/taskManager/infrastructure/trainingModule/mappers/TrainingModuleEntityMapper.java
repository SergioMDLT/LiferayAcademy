package com.example.taskManager.infrastructure.trainingModule.mappers;

import com.example.taskManager.domain.trainingModule.models.TrainingModule;
import com.example.taskManager.infrastructure.trainingModule.entities.TrainingModuleEntity;
import com.example.taskManager.infrastructure.task.mappers.TaskEntityMapper;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class TrainingModuleEntityMapper {

    private final TaskEntityMapper taskEntityMapper;

    public TrainingModuleEntityMapper(TaskEntityMapper taskEntityMapper) {
        this.taskEntityMapper = taskEntityMapper;
    }

    public TrainingModule toDomain(TrainingModuleEntity entity) {
        return new TrainingModule(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getTasks() != null
                ? entity.getTasks().stream().map(taskEntityMapper::toDomain).collect(Collectors.toList())
                : null
        );
    }

    public TrainingModuleEntity toEntity(TrainingModule domain) {
        TrainingModuleEntity entity = new TrainingModuleEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        // No se asignan tasks aquí por simplicidad
        return entity;
    }

}