package com.example.taskManager.infrastructure.task.mappers;

import com.example.taskManager.domain.task.models.Task;
import com.example.taskManager.infrastructure.task.entities.TaskEntity;
import com.example.taskManager.infrastructure.trainingModule.entities.TrainingModuleEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskEntityMapper {

    public TaskEntity toEntity(Task task) {
        TaskEntity entity = new TaskEntity();
        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());

        if (task.getTrainingModuleId() != null) {
            TrainingModuleEntity module = new TrainingModuleEntity();
            module.setId(task.getTrainingModuleId());
            entity.setTrainingModule(module);
        }

        return entity;
    }

    public Task toDomain(TaskEntity entity) {
        Task task = new Task();
        task.setId(entity.getId());
        task.setTitle(entity.getTitle());
        task.setDescription(entity.getDescription());

        if (entity.getTrainingModule() != null) {
            task.setTrainingModuleId(entity.getTrainingModule().getId());
        }

        return task;
    }
    
}