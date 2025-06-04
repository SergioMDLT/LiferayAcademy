package com.example.taskManager.application.task.usecase;

import org.springframework.stereotype.Service;
import com.example.taskManager.domain.assignedTask.interfaces.AssignedTaskRepositoryPort;
import com.example.taskManager.domain.task.exception.TaskNotFoundException;
import com.example.taskManager.domain.task.interfaces.TaskRepositoryPort;
import com.example.taskManager.domain.task.models.Task;
import jakarta.transaction.Transactional;

@Service
public class TaskDeleter {

    private final TaskRepositoryPort taskRepositoryPort;
    private final AssignedTaskRepositoryPort assignedTaskRepositoryPort;

    public TaskDeleter(
        TaskRepositoryPort taskRepositoryPort,
        AssignedTaskRepositoryPort assignedTaskRepositoryPort
    ) {
        this.taskRepositoryPort = taskRepositoryPort;
        this.assignedTaskRepositoryPort = assignedTaskRepositoryPort;
    }

    @Transactional
    public void execute(Integer taskId, Integer userId) {
        Task task = taskRepositoryPort.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        if (assignedTaskRepositoryPort.existsByTaskId(taskId)) {
            throw new IllegalStateException("No se puede eliminar una tarea que ya ha sido asignada.");
        }

        taskRepositoryPort.delete(task);
    }
    
}