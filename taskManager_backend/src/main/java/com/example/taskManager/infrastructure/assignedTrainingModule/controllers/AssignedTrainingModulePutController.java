package com.example.taskManager.infrastructure.assignedTrainingModule.controllers;

import com.example.taskManager.application.assignedTrainingModule.dtos.UpdateAssignedTrainingModuleCompletionInputDto;
import com.example.taskManager.application.assignedTrainingModule.dtos.UpdateAssignedTrainingModuleCompletionOutputDto;
import com.example.taskManager.application.assignedTrainingModule.usecase.AssignedTrainingModuleCompletionUpdater;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.PutAssignedTrainingModuleCompletionRequestDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.PutAssignedTrainingModuleCompletionResponseDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.mappers.PutAssignedTrainingModuleCompletionDtoMapper;
import com.example.taskManager.infrastructure.auth.services.AuthenticatedUserProvider;
import com.example.taskManager.infrastructure.auth.dtos.AuthenticatedUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assigned-training-modules")
@CrossOrigin("http://localhost:4200")
public class AssignedTrainingModulePutController {

    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final AssignedTrainingModuleCompletionUpdater useCase;
    private final PutAssignedTrainingModuleCompletionDtoMapper dtoMapper;

    public AssignedTrainingModulePutController(
        AuthenticatedUserProvider authenticatedUserProvider,
        AssignedTrainingModuleCompletionUpdater useCase,
        PutAssignedTrainingModuleCompletionDtoMapper dtoMapper
    ) {
        this.authenticatedUserProvider = authenticatedUserProvider;
        this.useCase = useCase;
        this.dtoMapper = dtoMapper;
    }

    @PutMapping("/{assignedTrainingModuleId}/completion")
    public ResponseEntity<PutAssignedTrainingModuleCompletionResponseDto> updateCompletion(
        @PathVariable Integer assignedTrainingModuleId,
        @RequestBody PutAssignedTrainingModuleCompletionRequestDto request
    ) {
        AuthenticatedUserDTO user = authenticatedUserProvider.execute(false);

        UpdateAssignedTrainingModuleCompletionInputDto input = dtoMapper.toInput(
            user.getUserId(),
            assignedTrainingModuleId,
            request
        );

        UpdateAssignedTrainingModuleCompletionOutputDto output = useCase.execute(input);
        return ResponseEntity.ok(dtoMapper.toResponse(output));
    }

}