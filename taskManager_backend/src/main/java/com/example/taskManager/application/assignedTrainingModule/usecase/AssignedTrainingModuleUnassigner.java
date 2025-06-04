package com.example.taskManager.application.assignedTrainingModule.usecase;

import com.example.taskManager.application.assignedTask.usecase.AssignedTaskDeleter;
import com.example.taskManager.application.assignedTrainingModule.dtos.DeleteAssignedTrainingModuleInputDto;
import com.example.taskManager.domain.assignedTask.interfaces.AssignedTaskRepositoryPort;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.domain.assignedTrainingModule.interfaces.AssignedTrainingModuleRepositoryPort;
import com.example.taskManager.domain.assignedTrainingModule.models.AssignedTrainingModule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssignedTrainingModuleUnassigner {

    private final AssignedTrainingModuleRepositoryPort repository;
    private final AssignedTaskRepositoryPort assignedTaskRepositoryPort;
    private final AssignedTaskDeleter assignedTaskDeleter;

    public AssignedTrainingModuleUnassigner(
        AssignedTrainingModuleRepositoryPort repository,
        AssignedTaskRepositoryPort assignedTaskRepositoryPort,
        AssignedTaskDeleter assignedTaskDeleter
    ) {
        this.repository = repository;
        this.assignedTaskRepositoryPort = assignedTaskRepositoryPort;
        this.assignedTaskDeleter = assignedTaskDeleter;
    }

    @Transactional
    public void execute(DeleteAssignedTrainingModuleInputDto input) {
        List<AssignedTrainingModule> assignedModules = repository.findByUserId(input.getUserId());

        AssignedTrainingModule target = assignedModules.stream()
            .filter(m -> m.getId().equals(input.getAssignedTrainingModuleId()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("El módulo no pertenece a este usuario."));

        List<AssignedTask> assignedTasks = assignedTaskRepositoryPort.findByUserIdAndTrainingModuleId(
            input.getUserId(),
            target.getTrainingModuleId(),
            null
        ).getContent();

        for (AssignedTask task : assignedTasks) {
            assignedTaskDeleter.execute(task.getId(), input.getUserId());
        }

        repository.delete(target);
    }
    
}