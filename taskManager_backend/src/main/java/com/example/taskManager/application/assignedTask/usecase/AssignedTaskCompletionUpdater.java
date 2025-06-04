package com.example.taskManager.application.assignedTask.usecase;

import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskCompletionInputDto;
import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskCompletionOutputDto;
import com.example.taskManager.application.assignedTask.mappers.UpdateAssignedTaskCompletionApplicationMapper;
import com.example.taskManager.domain.assignedTask.exceptions.AssignedTaskNotFoundException;
import com.example.taskManager.domain.assignedTask.interfaces.AssignedTaskRepositoryPort;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.domain.task.interfaces.TaskRepositoryPort;
import com.example.taskManager.domain.task.models.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssignedTaskCompletionUpdater {

    private final AssignedTaskRepositoryPort assignedTaskRepositoryPort;
    private final TaskRepositoryPort taskRepositoryPort;
    private final UpdateAssignedTaskCompletionApplicationMapper mapper;

    public AssignedTaskCompletionUpdater(
        AssignedTaskRepositoryPort assignedTaskRepositoryPort,
        TaskRepositoryPort taskRepositoryPort,
        UpdateAssignedTaskCompletionApplicationMapper mapper
    ) {
        this.assignedTaskRepositoryPort = assignedTaskRepositoryPort;
        this.taskRepositoryPort = taskRepositoryPort;
        this.mapper = mapper;
    }

    @Transactional
    public UpdateAssignedTaskCompletionOutputDto execute(UpdateAssignedTaskCompletionInputDto input) {
        AssignedTask task = assignedTaskRepositoryPort.findById(input.getAssignedTaskId())
            .orElseThrow(() -> new AssignedTaskNotFoundException("Assigned task not found"));

        if (!task.getUserId().equals(input.getUserId())) {
            throw new SecurityException("You do not have permission to update this task");
        }

        // Guardamos prioridades anteriores
        Integer previousAbsolutePriority = task.getAbsolutePriority();
        Integer previousModulePriority = task.getModulePriority();

        // Obtener el módulo de la tarea
        Task originalTask = taskRepositoryPort.findById(task.getTaskId())
            .orElseThrow(() -> new IllegalStateException("Original task not found"));
        Integer moduleId = originalTask.getTrainingModuleId();

        // Cambiamos el estado
        boolean nowCompleted = !task.getCompleted();
        task.setCompleted(nowCompleted);

        if (nowCompleted) {
            // Si ahora está completada, eliminamos prioridades y reajustamos
            task.setAbsolutePriority(null);
            task.setModulePriority(null);

            if (previousAbsolutePriority != null) {
                assignedTaskRepositoryPort.reduceAbsolutePrioritiesAfterCompletion(input.getUserId(), previousAbsolutePriority);
            }
            if (previousModulePriority != null && moduleId != null) {
                assignedTaskRepositoryPort.reducePrioritiesAfterCompletion(input.getUserId(), moduleId, previousModulePriority);
            }

        } else {
            // Si pasa a pendiente, le asignamos nuevas prioridades (al final)
            Integer newAbsolute = assignedTaskRepositoryPort
                .findMaxAbsolutePriority(input.getUserId())
                .orElse(0) + 1;
            Integer newModule = assignedTaskRepositoryPort
                .findMaxModulePriority(input.getUserId(), moduleId)
                .orElse(0) + 1;

            task.setAbsolutePriority(newAbsolute);
            task.setModulePriority(newModule);
        }

        AssignedTask updated = assignedTaskRepositoryPort.save(task);
        return mapper.toOutput(updated, originalTask);
    }
    
}