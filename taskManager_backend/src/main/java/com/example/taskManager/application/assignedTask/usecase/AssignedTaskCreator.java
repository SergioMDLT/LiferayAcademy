package com.example.taskManager.application.assignedTask.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.taskManager.application.assignedTask.dtos.CreateAssignedTaskInputDto;
import com.example.taskManager.application.assignedTask.dtos.CreateAssignedTaskOutputDto;
import com.example.taskManager.application.assignedTask.mappers.CreateAssignedTaskApplicationMapper;
import com.example.taskManager.domain.assignedTask.interfaces.AssignedTaskRepositoryPort;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.domain.task.interfaces.TaskRepositoryPort;
import com.example.taskManager.domain.task.models.Task;

@Service
public class AssignedTaskCreator {

    private final AssignedTaskRepositoryPort assignedTaskRepositoryPort;
    private final TaskRepositoryPort taskRepositoryPort;
    private final CreateAssignedTaskApplicationMapper mapper;

    public AssignedTaskCreator(
        AssignedTaskRepositoryPort assignedTaskRepositoryPort,
        TaskRepositoryPort taskRepositoryPort,
        CreateAssignedTaskApplicationMapper mapper
    ) {
        this.assignedTaskRepositoryPort = assignedTaskRepositoryPort;
        this.taskRepositoryPort = taskRepositoryPort;
        this.mapper = mapper;
    }

    @Transactional
    public CreateAssignedTaskOutputDto execute(CreateAssignedTaskInputDto input) {
        AssignedTask assignedTask = mapper.toDomain(input);

        if (assignedTask.getAbsolutePriority() == null) {
            Integer max = assignedTaskRepositoryPort
                .findMaxAbsolutePriority(assignedTask.getUserId())
                .orElse(0);
            assignedTask.setAbsolutePriority(max + 1);
        }

        if (assignedTask.getModulePriority() == null) {
            Task task = taskRepositoryPort.findById(assignedTask.getTaskId())
                .orElseThrow(() -> new IllegalStateException("Task not found"));

            Integer moduleId = task.getTrainingModuleId();

            Integer max = assignedTaskRepositoryPort
                .findMaxModulePriority(assignedTask.getUserId(), moduleId)
                .orElse(0);
            assignedTask.setModulePriority(max + 1);
        }

        AssignedTask saved = assignedTaskRepositoryPort.save(assignedTask);
        return mapper.toDto(saved);
    }

}