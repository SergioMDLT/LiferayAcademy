package com.example.taskManager.infrastructure.trainingModule.controllers;

import com.example.taskManager.application.trainingModule.dtos.CreateTrainingModuleInputDto;
import com.example.taskManager.application.trainingModule.dtos.CreateTrainingModuleOutputDto;
import com.example.taskManager.application.trainingModule.usecase.TrainingModuleCreator;
import com.example.taskManager.infrastructure.trainingModule.dtos.PostTrainingModuleRequestDto;
import com.example.taskManager.infrastructure.trainingModule.dtos.PostTrainingModuleResponseDto;
import com.example.taskManager.infrastructure.trainingModule.mappers.PostTrainingModuleDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/training-modules")
@CrossOrigin("http://localhost:4200")
public class TrainingModulePostController {

    private final TrainingModuleCreator useCase;
    private final PostTrainingModuleDtoMapper dtoMapper;

    public TrainingModulePostController(
        TrainingModuleCreator useCase,
        PostTrainingModuleDtoMapper dtoMapper
    ) {
        this.useCase = useCase;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public ResponseEntity<PostTrainingModuleResponseDto> createTrainingModule(
        @RequestBody PostTrainingModuleRequestDto request
    ) {
        CreateTrainingModuleInputDto input = dtoMapper.toInput(request);
        CreateTrainingModuleOutputDto output = useCase.execute(input);
        return ResponseEntity.ok(dtoMapper.toResponse(output));
    }
    
}