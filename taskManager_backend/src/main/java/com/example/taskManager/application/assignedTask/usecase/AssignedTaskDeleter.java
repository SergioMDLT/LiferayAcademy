package com.example.taskManager.application.assignedTask.usecase;

import com.example.taskManager.domain.assignedTask.exceptions.AssignedTaskNotFoundException;
import com.example.taskManager.domain.assignedTask.interfaces.AssignedTaskRepositoryPort;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.domain.task.interfaces.TaskRepositoryPort;
import com.example.taskManager.domain.task.models.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssignedTaskDeleter {

    private final AssignedTaskRepositoryPort assignedTaskRepositoryPort;
    private final TaskRepositoryPort taskRepositoryPort;

    public AssignedTaskDeleter(
        AssignedTaskRepositoryPort assignedTaskRepositoryPort,
        TaskRepositoryPort taskRepositoryPort
    ) {
        this.assignedTaskRepositoryPort = assignedTaskRepositoryPort;
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Transactional
    public void execute(Integer assignedTaskId, Integer userId) {
        AssignedTask task = assignedTaskRepositoryPort.findById(assignedTaskId)
            .orElseThrow(() -> new AssignedTaskNotFoundException("Assigned task not found"));

        if (!task.getUserId().equals(userId)) {
            throw new SecurityException("You do not have permission to delete this task");
        }

        Integer deletedAbsolutePriority = task.getAbsolutePriority();
        Integer deletedModulePriority = task.getModulePriority();
        Integer taskId = task.getTaskId();

        Task originalTask = taskRepositoryPort.findById(taskId)
            .orElseThrow(() -> new IllegalStateException("Original task not found"));
        Integer trainingModuleId = originalTask.getTrainingModuleId();

        assignedTaskRepositoryPort.delete(task);

        if (deletedAbsolutePriority != null) {
            assignedTaskRepositoryPort.reduceAbsolutePrioritiesAfterCompletion(userId, deletedAbsolutePriority);
        }

        if (deletedModulePriority != null && trainingModuleId != null) {
            assignedTaskRepositoryPort.reducePrioritiesAfterCompletion(userId, trainingModuleId, deletedModulePriority);
        }
    }
}