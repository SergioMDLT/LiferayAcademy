package com.example.taskManager.infrastructure.task.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.taskManager.application.task.usecase.TaskUpdater;
import com.example.taskManager.infrastructure.task.dtos.UpdateTaskRequestDto;
import com.example.taskManager.infrastructure.task.mappers.PatchTaskDtoMapper;

@RestController
@RequestMapping("/tasks")
@CrossOrigin("http://localhost:4200")
public class TaskPatchController {

    private final TaskUpdater taskUpdater;
    private final PatchTaskDtoMapper patchMapper;

    public TaskPatchController(
        TaskUpdater taskUpdater,
        PatchTaskDtoMapper patchMapper
    ) {
        this.taskUpdater = taskUpdater;
        this.patchMapper = patchMapper;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Integer id, @RequestBody UpdateTaskRequestDto request) {
        taskUpdater.execute(id, patchMapper.toInput(request));
        return ResponseEntity.noContent().build();
    }
    
}