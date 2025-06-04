package com.example.taskManager.application.assignedTask.usecase;

import com.example.taskManager.application.assignedTask.dtos.GetAssignedTaskOutputDto;
import com.example.taskManager.application.assignedTask.mappers.GetAssignedTaskApplicationMapper;
import com.example.taskManager.domain.assignedTask.exceptions.AssignedTaskNotFoundException;
import com.example.taskManager.domain.assignedTask.interfaces.AssignedTaskRepositoryPort;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.domain.task.interfaces.TaskRepositoryPort;
import com.example.taskManager.domain.task.models.Task;
import org.springframework.stereotype.Service;

@Service
public class AssignedTaskGetter {

    private final AssignedTaskRepositoryPort assignedTaskRepositoryPort;
    private final TaskRepositoryPort taskRepositoryPort;
    private final GetAssignedTaskApplicationMapper mapper;

    public AssignedTaskGetter(
        AssignedTaskRepositoryPort assignedTaskRepositoryPort,
        TaskRepositoryPort taskRepositoryPort,
        GetAssignedTaskApplicationMapper mapper
    ) {
        this.assignedTaskRepositoryPort = assignedTaskRepositoryPort;
        this.taskRepositoryPort = taskRepositoryPort;
        this.mapper = mapper;
    }

    public GetAssignedTaskOutputDto execute(Integer id, Integer userId) {
        AssignedTask assignedTask = assignedTaskRepositoryPort.findById(id)
            .orElseThrow(() -> new AssignedTaskNotFoundException("Assigned task not found with id: " + id));

        if (!assignedTask.getUserId().equals(userId)) {
            throw new SecurityException("No permission to access this assigned task");
        }

        Task task = taskRepositoryPort.findById(assignedTask.getTaskId())
            .orElseThrow(() -> new IllegalStateException("Associated task not found"));

        return mapper.toDto(assignedTask, task);
    }

}