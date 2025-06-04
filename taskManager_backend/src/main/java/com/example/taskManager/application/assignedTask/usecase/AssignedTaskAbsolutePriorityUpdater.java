package com.example.taskManager.application.assignedTask.usecase;

import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskAbsolutePriorityInputDto;
import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskAbsolutePriorityOutputDto;
import com.example.taskManager.application.assignedTask.mappers.UpdateAssignedTaskAbsolutePriorityApplicationMapper;
import com.example.taskManager.domain.assignedTask.exceptions.AssignedTaskNotFoundException;
import com.example.taskManager.domain.assignedTask.interfaces.AssignedTaskRepositoryPort;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.domain.task.interfaces.TaskRepositoryPort;
import com.example.taskManager.domain.task.models.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssignedTaskAbsolutePriorityUpdater {

    private final AssignedTaskRepositoryPort assignedTaskRepository;
    private final TaskRepositoryPort taskRepository;
    private final UpdateAssignedTaskAbsolutePriorityApplicationMapper mapper;

    public AssignedTaskAbsolutePriorityUpdater(
        AssignedTaskRepositoryPort assignedTaskRepository,
        TaskRepositoryPort taskRepository,
        UpdateAssignedTaskAbsolutePriorityApplicationMapper mapper
    ) {
        this.assignedTaskRepository = assignedTaskRepository;
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Transactional
    public UpdateAssignedTaskAbsolutePriorityOutputDto execute(UpdateAssignedTaskAbsolutePriorityInputDto input) {
        AssignedTask assignedTask = assignedTaskRepository.findById(input.getAssignedTaskId())
            .orElseThrow(() -> new AssignedTaskNotFoundException("Assigned task not found"));

        if (!assignedTask.getUserId().equals(input.getUserId())) {
            throw new SecurityException("No permission to update this task");
        }

        assignedTask.setAbsolutePriority(input.getNewPriority());
        assignedTaskRepository.save(assignedTask);

        Task task = taskRepository.findById(assignedTask.getTaskId())
            .orElseThrow(() -> new IllegalStateException("Original task not found"));

        return mapper.toOutput(assignedTask, task);
    }

}