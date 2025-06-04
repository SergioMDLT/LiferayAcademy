package com.example.taskManager.application.assignedTask.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskCompletionInputDto;
import com.example.taskManager.application.assignedTask.dtos.UpdateAssignedTaskCompletionOutputDto;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.domain.task.models.Task;

@Component
public class UpdateAssignedTaskCompletionApplicationMapper {

    public UpdateAssignedTaskCompletionInputDto toInput(Integer id, Integer userId) {
        return new UpdateAssignedTaskCompletionInputDto(id, userId);
    }

    public UpdateAssignedTaskCompletionOutputDto toOutput(AssignedTask assignedTask, Task task) {
        return new UpdateAssignedTaskCompletionOutputDto(
            assignedTask.getId(),
            task.getTitle(),
            task.getDescription(),
            assignedTask.getCompleted(),
            assignedTask.getModulePriority(),
            assignedTask.getAbsolutePriority()
        );
    }

}