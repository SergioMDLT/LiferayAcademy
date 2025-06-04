package com.example.taskManager.application.assignedTask.mappers;

import com.example.taskManager.application.assignedTask.dtos.SearchAssignedTaskOutputDto;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.domain.task.interfaces.TaskRepositoryPort;
import com.example.taskManager.domain.task.models.Task;
import org.springframework.stereotype.Component;

@Component
public class SearchAssignedTaskApplicationMapper {

    private final TaskRepositoryPort taskRepositoryPort;

    public SearchAssignedTaskApplicationMapper(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    public SearchAssignedTaskOutputDto toDto(AssignedTask assignedTask) {
        Task task = taskRepositoryPort.findById(assignedTask.getTaskId())
            .orElseThrow(() -> new IllegalStateException("Task not found for assignedTaskId " + assignedTask.getId()));

        return new SearchAssignedTaskOutputDto(
            assignedTask.getId(),
            task.getTitle(),
            task.getDescription(),
            assignedTask.getCompleted(),
            assignedTask.getModulePriority(),
            assignedTask.getAbsolutePriority(),
            task.getTrainingModuleId(),
            assignedTask.getUserId(),
            task.getId()
        );
    }
    
}