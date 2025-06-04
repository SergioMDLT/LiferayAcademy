package com.example.taskManager.application.assignedTask.mappers;

import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskAbsolutePriorityOutputDto;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.domain.task.models.Task;
import org.springframework.stereotype.Component;

@Component
public class UpdateAssignedTaskAbsolutePriorityApplicationMapper {

    public UpdateAssignedTaskAbsolutePriorityOutputDto toOutput(AssignedTask assignedTask, Task task) {
        return new UpdateAssignedTaskAbsolutePriorityOutputDto(
            assignedTask.getId(),
            task.getTitle(),
            task.getDescription(),
            assignedTask.getCompleted(),
            assignedTask.getAbsolutePriority()
        );
    }
    
}