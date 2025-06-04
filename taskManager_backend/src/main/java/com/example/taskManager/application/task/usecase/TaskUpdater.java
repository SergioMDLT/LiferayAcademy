package com.example.taskManager.application.task.usecase;

import org.springframework.stereotype.Service;
import com.example.taskManager.application.task.dtos.UpdateTaskInputDto;
import com.example.taskManager.domain.task.exception.TaskNotFoundException;
import com.example.taskManager.domain.task.interfaces.TaskRepositoryPort;
import com.example.taskManager.domain.task.models.Task;
import jakarta.transaction.Transactional;

@Service
public class TaskUpdater {

    private final TaskRepositoryPort taskRepositoryPort;

    public TaskUpdater(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Transactional
    public void execute(Integer taskId, UpdateTaskInputDto dto) {
        taskRepositoryPort.findById(taskId)
            .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));

        Task updated = new Task(
            taskId,
            dto.getTitle(),
            dto.getDescription(),
            dto.getTrainingModuleId()
        );

        taskRepositoryPort.save(updated);
    }

}