package com.example.taskManager.infrastructure.assignedTask.controllers;

import com.example.taskManager.application.assignedTask.dtos.*;
import com.example.taskManager.application.assignedTask.usecase.*;
import com.example.taskManager.infrastructure.auth.dtos.AuthenticatedUserDTO;
import com.example.taskManager.infrastructure.auth.services.AuthenticatedUserProvider;
import com.example.taskManager.infrastructure.assignedTask.dtos.*;
import com.example.taskManager.infrastructure.assignedTask.mappers.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assigned-tasks")
@CrossOrigin("http://localhost:4200")
public class AssignedTaskPatchController {

    private final AuthenticatedUserProvider userProvider;
    private final AssignedTaskCompletionUpdater completionUpdater;
    private final AssignedTaskModulePriorityUpdater modulePriorityUpdater;
    private final AssignedTaskAbsolutePriorityUpdater absolutePriorityUpdater;
    private final PatchAssignedTaskCompletionDtoMapper completionMapper;
    private final PatchAssignedTaskModulePriorityDtoMapper modulePriorityMapper;
    private final PatchAssignedTaskAbsolutePriorityDtoMapper absolutePriorityMapper;

    public AssignedTaskPatchController(
        AuthenticatedUserProvider userProvider,
        AssignedTaskCompletionUpdater completionUpdater,
        AssignedTaskModulePriorityUpdater modulePriorityUpdater,
        AssignedTaskAbsolutePriorityUpdater absolutePriorityUpdater,
        PatchAssignedTaskCompletionDtoMapper completionMapper,
        PatchAssignedTaskModulePriorityDtoMapper modulePriorityMapper,
        PatchAssignedTaskAbsolutePriorityDtoMapper absolutePriorityMapper
    ) {
        this.userProvider = userProvider;
        this.completionUpdater = completionUpdater;
        this.modulePriorityUpdater = modulePriorityUpdater;
        this.absolutePriorityUpdater = absolutePriorityUpdater;
        this.completionMapper = completionMapper;
        this.modulePriorityMapper = modulePriorityMapper;
        this.absolutePriorityMapper = absolutePriorityMapper;
    }

    @PatchMapping("/{id}/completion")
    public ResponseEntity<PatchAssignedTaskCompletionResponseDto> toggleCompletion(@PathVariable Integer id) {
        AuthenticatedUserDTO user = userProvider.execute(true);
        UpdateAssignedTaskCompletionInputDto input = new UpdateAssignedTaskCompletionInputDto(id, user.getUserId());
        UpdateAssignedTaskCompletionOutputDto output = completionUpdater.execute(input);
        return ResponseEntity.ok(completionMapper.toResponse(output));
    }

    @PatchMapping("/{id}/priority/module")
    public ResponseEntity<PatchAssignedTaskModulePriorityResponseDto> updateModulePriority(
        @PathVariable Integer id,
        @RequestBody PatchAssignedTaskModulePriorityRequestDto request
    ) {
        AuthenticatedUserDTO user = userProvider.execute(true);
        UpdateAssignedTaskModulePriorityInputDto input = new UpdateAssignedTaskModulePriorityInputDto(id, user.getUserId(), request.getModulePriority());
        UpdateAssignedTaskModulePriorityOutputDto output = modulePriorityUpdater.execute(input);
        return ResponseEntity.ok(modulePriorityMapper.toResponse(output));
    }

    @PatchMapping("/{id}/priority/absolute")
    public ResponseEntity<PatchAssignedTaskAbsolutePriorityResponseDto> updateAbsolutePriority(
        @PathVariable Integer id,
        @RequestBody PatchAssignedTaskAbsolutePriorityRequestDto request
    ) {
        AuthenticatedUserDTO user = userProvider.execute(true);
        UpdateAssignedTaskAbsolutePriorityInputDto input = new UpdateAssignedTaskAbsolutePriorityInputDto(id, user.getUserId(), request.getAbsolutePriority());
        UpdateAssignedTaskAbsolutePriorityOutputDto output = absolutePriorityUpdater.execute(input);
        return ResponseEntity.ok(absolutePriorityMapper.toResponse(output));
    }
    
}