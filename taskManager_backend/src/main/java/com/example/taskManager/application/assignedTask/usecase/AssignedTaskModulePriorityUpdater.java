package com.example.taskManager.application.assignedTask.usecase;

import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskModulePriorityInputDto;
import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskModulePriorityOutputDto;
import com.example.taskManager.application.assignedTask.mappers.UpdateAssignedTaskModulePriorityApplicationMapper;
import com.example.taskManager.domain.assignedTask.exceptions.AssignedTaskNotFoundException;
import com.example.taskManager.domain.assignedTask.interfaces.AssignedTaskRepositoryPort;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.domain.task.interfaces.TaskRepositoryPort;
import com.example.taskManager.domain.task.models.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssignedTaskModulePriorityUpdater {

    private final AssignedTaskRepositoryPort assignedTaskRepository;
    private final TaskRepositoryPort taskRepository;
    private final UpdateAssignedTaskModulePriorityApplicationMapper mapper;

    public AssignedTaskModulePriorityUpdater(
        AssignedTaskRepositoryPort assignedTaskRepository,
        TaskRepositoryPort taskRepository,
        UpdateAssignedTaskModulePriorityApplicationMapper mapper
    ) {
        this.assignedTaskRepository = assignedTaskRepository;
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Transactional
    public UpdateAssignedTaskModulePriorityOutputDto execute(UpdateAssignedTaskModulePriorityInputDto input) {
        AssignedTask task = assignedTaskRepository.findById(input.getAssignedTaskId())
            .orElseThrow(() -> new AssignedTaskNotFoundException("Assigned task not found"));

        if (!task.getUserId().equals(input.getUserId())) {
            throw new SecurityException("No permission to update this task");
        }

        task.setModulePriority(input.getNewPriority());
        assignedTaskRepository.save(task);

        Task originalTask = taskRepository.findById(task.getTaskId())
            .orElseThrow(() -> new IllegalStateException("Original task not found"));

        return mapper.toOutput(task, originalTask);
    }
    
}