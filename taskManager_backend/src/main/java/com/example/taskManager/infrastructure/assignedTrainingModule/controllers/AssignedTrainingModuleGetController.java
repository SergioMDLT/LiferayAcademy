package com.example.taskManager.infrastructure.assignedTrainingModule.controllers;

import com.example.taskManager.application.assignedTrainingModule.dtos.GetAllAssignedTrainingModuleOutputDto;
import com.example.taskManager.application.assignedTrainingModule.dtos.GetAssignedTrainingModuleOutputDto;
import com.example.taskManager.application.assignedTrainingModule.usecase.AssignedTrainingModuleGetterByUser;
import com.example.taskManager.application.assignedTrainingModule.usecase.AssignedTrainingModuleLister;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.GetAllAssignedTrainingModuleResponseDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.dtos.GetAssignedTrainingModuleResponseDto;
import com.example.taskManager.infrastructure.assignedTrainingModule.mappers.GetAssignedTrainingModuleDtoMapper;
import com.example.taskManager.infrastructure.auth.services.AuthenticatedUserProvider;
import com.example.taskManager.infrastructure.auth.dtos.AuthenticatedUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/assigned-training-modules")
@CrossOrigin("http://localhost:4200")
public class AssignedTrainingModuleGetController {

    private final AssignedTrainingModuleGetterByUser useCase;
    private final AssignedTrainingModuleLister listerUseCase;
    private final GetAssignedTrainingModuleDtoMapper dtoMapper;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public AssignedTrainingModuleGetController(
        AssignedTrainingModuleGetterByUser useCase,
        AssignedTrainingModuleLister listerUseCase,
        GetAssignedTrainingModuleDtoMapper dtoMapper,
        AuthenticatedUserProvider authenticatedUserProvider
    ) {
        this.useCase = useCase;
        this.listerUseCase = listerUseCase;
        this.dtoMapper = dtoMapper;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @GetMapping
    public ResponseEntity<List<GetAssignedTrainingModuleResponseDto>> getModulesForAuthenticatedUser() {
        AuthenticatedUserDTO user = authenticatedUserProvider.execute(false);
        List<GetAssignedTrainingModuleOutputDto> outputList = useCase.execute(user.getUserId());
        List<GetAssignedTrainingModuleResponseDto> responseList = dtoMapper.toResponseList(outputList);
        return ResponseEntity.ok(responseList);
        
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetAllAssignedTrainingModuleResponseDto>> getAllAssignedModules() {
        List<GetAllAssignedTrainingModuleOutputDto> outputList = listerUseCase.execute();
        return ResponseEntity.ok(dtoMapper.toResponseListAll(outputList));
    }

}