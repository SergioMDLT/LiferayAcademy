package com.example.taskManager.infrastructure.assignedTask.mappers;

import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.infrastructure.assignedTask.entities.AssignedTaskEntity;
import com.example.taskManager.infrastructure.task.entities.TaskEntity;
import com.example.taskManager.infrastructure.user.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class AssignedTaskEntityMapper {

    public AssignedTaskEntity toEntity(AssignedTask assignedTask) {
        AssignedTaskEntity entity = new AssignedTaskEntity();

        entity.setId(assignedTask.getId());
        entity.setCompleted(assignedTask.getCompleted());
        entity.setModulePriority(assignedTask.getModulePriority());
        entity.setAbsolutePriority(assignedTask.getAbsolutePriority());

        if (assignedTask.getUserId() != null) {
            UserEntity user = new UserEntity();
            user.setId(assignedTask.getUserId());
            entity.setUser(user);
        }

        if (assignedTask.getTaskId() != null) {
            TaskEntity task = new TaskEntity();
            task.setId(assignedTask.getTaskId());
            entity.setTask(task);
        }

        return entity;
    }

    public AssignedTask toDomain(AssignedTaskEntity entity) {
        AssignedTask assignedTask = new AssignedTask();

        assignedTask.setId(entity.getId());
        assignedTask.setCompleted(entity.getCompleted());
        assignedTask.setModulePriority(entity.getModulePriority());
        assignedTask.setAbsolutePriority(entity.getAbsolutePriority());

        if (entity.getUser() != null) {
            assignedTask.setUserId(entity.getUser().getId());
        }

        if (entity.getTask() != null) {
            assignedTask.setTaskId(entity.getTask().getId());
        }

        return assignedTask;
    }

}