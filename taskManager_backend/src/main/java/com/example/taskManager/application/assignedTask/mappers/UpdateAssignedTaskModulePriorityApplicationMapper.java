package com.example.taskManager.application.assignedTask.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskModulePriorityOutputDto;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.domain.task.models.Task;

@Component
public class UpdateAssignedTaskModulePriorityApplicationMapper {
    
    public UpdateAssignedTaskModulePriorityOutputDto toOutput(AssignedTask assignedTask, Task task) {
        return new UpdateAssignedTaskModulePriorityOutputDto(
            assignedTask.getId(),
            task.getTitle(),
            task.getDescription(),
            assignedTask.getCompleted(),
            assignedTask.getModulePriority()
        );
    }
    
}