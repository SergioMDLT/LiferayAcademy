package com.example.taskManager.application.assignedTrainingModule.usecase;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskManager.application.assignedTrainingModule.dtos.CreateAssignedTrainingModuleInputDto;
import com.example.taskManager.application.assignedTrainingModule.dtos.CreateAssignedTrainingModuleOutputDto;
import com.example.taskManager.application.assignedTrainingModule.mappers.CreateAssignedTrainingModuleApplicationMapper;
import com.example.taskManager.domain.assignedTask.interfaces.AssignedTaskRepositoryPort;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.domain.assignedTrainingModule.interfaces.AssignedTrainingModuleRepositoryPort;
import com.example.taskManager.domain.assignedTrainingModule.models.AssignedTrainingModule;
import com.example.taskManager.domain.task.interfaces.TaskRepositoryPort;
import com.example.taskManager.domain.task.models.Task;

@Service
public class AssignedTrainingModuleCreator {

    private final AssignedTaskRepositoryPort assignedTaskRepository;
    private final AssignedTrainingModuleRepositoryPort repository;
    private final CreateAssignedTrainingModuleApplicationMapper mapper;
    private final TaskRepositoryPort taskRepository;

    public AssignedTrainingModuleCreator(
        AssignedTaskRepositoryPort assignedTaskRepository,
        AssignedTrainingModuleRepositoryPort repository,
        CreateAssignedTrainingModuleApplicationMapper mapper,
        TaskRepositoryPort taskRepository
    ) {
        this.assignedTaskRepository = assignedTaskRepository;
        this.repository = repository;
        this.mapper = mapper;
        this.taskRepository = taskRepository;
    }

    @Transactional
    public CreateAssignedTrainingModuleOutputDto execute(CreateAssignedTrainingModuleInputDto input) {
        if (repository.existsByUserIdAndTrainingModuleId(input.getUserId(), input.getTrainingModuleId())) {
            throw new IllegalStateException("Este módulo ya está asignado al usuario.");
        }

        AssignedTrainingModule assigned = mapper.toDomain(input);
        AssignedTrainingModule saved = repository.save(assigned);

        List<Task> tasks = taskRepository.findByTrainingModuleId(input.getTrainingModuleId());

        for (Task task : tasks) {
            AssignedTask assignedTask = new AssignedTask();
            assignedTask.setUserId(input.getUserId());
            assignedTask.setTaskId(task.getId());
            assignedTask.setCompleted(false);

            Integer maxAbsolute = assignedTaskRepository.findMaxAbsolutePriority(input.getUserId()).orElse(0);
            assignedTask.setAbsolutePriority(maxAbsolute + 1);

            Integer maxModule = assignedTaskRepository.findMaxModulePriority(input.getUserId(), task.getTrainingModuleId()).orElse(0);
            assignedTask.setModulePriority(maxModule + 1);

            assignedTaskRepository.save(assignedTask);
        }

        return mapper.toDTO(saved);
    }

}