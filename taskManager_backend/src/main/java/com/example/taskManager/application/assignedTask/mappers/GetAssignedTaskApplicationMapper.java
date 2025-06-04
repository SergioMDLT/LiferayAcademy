package com.example.taskManager.application.assignedTask.mappers;

import com.example.taskManager.application.assignedTask.dtos.GetAssignedTaskOutputDto;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.domain.task.models.Task;
import org.springframework.stereotype.Component;

@Component
public class GetAssignedTaskApplicationMapper {

    public GetAssignedTaskOutputDto toDto(AssignedTask assignedTask, Task task) {
        return new GetAssignedTaskOutputDto(
            assignedTask.getId(),
            task.getTitle(),
            task.getDescription(),
            assignedTask.getCompleted(),
            assignedTask.getModulePriority(),
            assignedTask.getAbsolutePriority(),
            task.getTrainingModuleId()
        );
    }
    
}