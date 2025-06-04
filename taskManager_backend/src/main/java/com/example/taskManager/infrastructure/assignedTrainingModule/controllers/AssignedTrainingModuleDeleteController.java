package com.example.taskManager.infrastructure.assignedTrainingModule.controllers;

import com.example.taskManager.application.assignedTrainingModule.dtos.DeleteAssignedTrainingModuleInputDto;
import com.example.taskManager.application.assignedTrainingModule.usecase.AssignedTrainingModuleUnassigner;
import com.example.taskManager.infrastructure.assignedTrainingModule.mappers.DeleteAssignedTrainingModuleDtoMapper;
import com.example.taskManager.infrastructure.auth.services.AuthenticatedUserProvider;
import com.example.taskManager.infrastructure.auth.dtos.AuthenticatedUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assigned-training-modules")
@CrossOrigin("http://localhost:4200")
public class AssignedTrainingModuleDeleteController {

    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final AssignedTrainingModuleUnassigner useCase;
    private final DeleteAssignedTrainingModuleDtoMapper dtoMapper;

    public AssignedTrainingModuleDeleteController(
        AuthenticatedUserProvider authenticatedUserProvider,
        AssignedTrainingModuleUnassigner useCase,
        DeleteAssignedTrainingModuleDtoMapper dtoMapper
    ) {
        this.authenticatedUserProvider = authenticatedUserProvider;
        this.useCase = useCase;
        this.dtoMapper = dtoMapper;
    }

    @DeleteMapping("/{assignedTrainingModuleId}")
    public ResponseEntity<Void> unassignTrainingModule(@PathVariable Integer assignedTrainingModuleId) {
        AuthenticatedUserDTO user = authenticatedUserProvider.execute(false);

        DeleteAssignedTrainingModuleInputDto input = dtoMapper.toInput(user.getUserId(), assignedTrainingModuleId);
        useCase.execute(input);

        return ResponseEntity.noContent().build();
    }
    
}