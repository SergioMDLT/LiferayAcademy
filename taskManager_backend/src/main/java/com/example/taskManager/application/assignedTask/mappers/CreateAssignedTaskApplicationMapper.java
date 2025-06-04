package com.example.taskManager.application.assignedTask.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.application.assignedTask.dtos.CreateAssignedTaskInputDto;
import com.example.taskManager.application.assignedTask.dtos.CreateAssignedTaskOutputDto;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;

@Component
public class CreateAssignedTaskApplicationMapper {

    public AssignedTask toDomain(CreateAssignedTaskInputDto input) {
        return new AssignedTask(
            null,
            input.getUserId(),
            input.getTaskId(),
            false,
            null,
            null
        );
    }

    public CreateAssignedTaskOutputDto toDto(AssignedTask assignedTask) {
        return new CreateAssignedTaskOutputDto(
            assignedTask.getId(),
            assignedTask.getTaskId(),
            assignedTask.getCompleted(),
            assignedTask.getModulePriority(),
            assignedTask.getAbsolutePriority()
        );
    }

}