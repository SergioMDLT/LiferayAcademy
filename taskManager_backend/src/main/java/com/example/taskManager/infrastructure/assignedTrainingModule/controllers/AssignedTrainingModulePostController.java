package com.example.taskManager.infrastructure.assignedTrainingModule.controllers;

import com.example.taskManager.application.assignedTrainingModule.dtos.CreateAssignedTrainingModuleInputDto;
import com.example.taskManager.application.assignedTrainingModule.dtos.CreateAssignedTrainingModuleOutputDto;
import com.example.taskManager.application.assignedTrainingModule.usecase.AssignedTrainingModuleCreator;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.CreateAssignedTrainingModuleAdminRequestDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.CreateAssignedTrainingModuleRequestDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.CreateAssignedTrainingModuleResponseDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.mappers.PostAssignedTrainingModuleDtoMapper;
import com.example.taskManager.infrastructure.auth.services.AuthenticatedUserProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assigned-training-modules")
@CrossOrigin("http://localhost:4200")
public class AssignedTrainingModulePostController {

    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final AssignedTrainingModuleCreator useCase;
    private final PostAssignedTrainingModuleDtoMapper dtoMapper;

    public AssignedTrainingModulePostController(
        AuthenticatedUserProvider authenticatedUserProvider,
        AssignedTrainingModuleCreator useCase,
        PostAssignedTrainingModuleDtoMapper dtoMapper
    ) {
        this.authenticatedUserProvider = authenticatedUserProvider;
        this.useCase = useCase;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public ResponseEntity<CreateAssignedTrainingModuleResponseDto> assignTrainingModule(
        @RequestBody CreateAssignedTrainingModuleRequestDto request
    ) {
        Integer userId = authenticatedUserProvider.execute(true).getUserId();

        CreateAssignedTrainingModuleInputDto input = dtoMapper.toInput(request, userId);
        CreateAssignedTrainingModuleOutputDto output = useCase.execute(input);

        return ResponseEntity.ok(dtoMapper.toResponse(output));
    }
    
    @PostMapping("/admin")
    public ResponseEntity<CreateAssignedTrainingModuleResponseDto> assignTrainingModuleAsAdmin(
        @RequestBody CreateAssignedTrainingModuleAdminRequestDto request
    ) {
        CreateAssignedTrainingModuleInputDto input = dtoMapper.toInput(request);
        CreateAssignedTrainingModuleOutputDto output = useCase.execute(input);
        return ResponseEntity.ok(dtoMapper.toResponse(output));
    }
}
