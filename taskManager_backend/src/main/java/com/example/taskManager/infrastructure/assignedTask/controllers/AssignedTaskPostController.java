package com.example.taskManager.infrastructure.assignedTask.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.taskManager.application.assignedTask.dtos.CreateAssignedTaskInputDto;
import com.example.taskManager.application.assignedTask.dtos.CreateAssignedTaskOutputDto;
import com.example.taskManager.application.assignedTask.usecase.AssignedTaskCreator;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.infrastructure.assignedTask.dtos.CreateAssignedTaskAdminRequestDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.CreateAssignedTaskRequestDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.CreateAssignedTaskResponseDto;
import com.example.taskManager.infrastructure.assignedTask.mappers.PostAssignedTaskDtoMapper;
import com.example.taskManager.infrastructure.auth.services.AuthenticatedUserProvider;

@RestController
@RequestMapping("/assigned-tasks")
@CrossOrigin("http://localhost:4200")
public class AssignedTaskPostController {

    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final AssignedTaskCreator assignedTaskCreator;
    private final PostAssignedTaskDtoMapper postAssignedTaskDtoMapper;

    public AssignedTaskPostController(
        AuthenticatedUserProvider authenticatedUserProvider,
        AssignedTaskCreator assignedTaskCreator,
        PostAssignedTaskDtoMapper postAssignedTaskDtoMapper
    ) {
        this.authenticatedUserProvider = authenticatedUserProvider;
        this.assignedTaskCreator = assignedTaskCreator;
        this.postAssignedTaskDtoMapper = postAssignedTaskDtoMapper;
    }

    @PostMapping
    public ResponseEntity<CreateAssignedTaskResponseDto> createAssignedTask(
        @RequestBody CreateAssignedTaskRequestDto requestDto
    ) {
        Integer userId = authenticatedUserProvider.execute(true).getUserId();
        CreateAssignedTaskInputDto inputDto = postAssignedTaskDtoMapper.toInput(requestDto, userId);
        CreateAssignedTaskOutputDto outputDto = assignedTaskCreator.execute(inputDto);
        return ResponseEntity.ok(postAssignedTaskDtoMapper.toResponse(outputDto));
    }

    @PostMapping("/admin")
    public ResponseEntity<CreateAssignedTaskResponseDto> createAssignedTaskAsAdmin(
        @RequestBody CreateAssignedTaskAdminRequestDto requestDto
    ) {
        CreateAssignedTaskInputDto inputDto = postAssignedTaskDtoMapper.toInput(requestDto);
        CreateAssignedTaskOutputDto outputDto = assignedTaskCreator.execute(inputDto);
        return ResponseEntity.ok(postAssignedTaskDtoMapper.toResponse(outputDto));
    }

}