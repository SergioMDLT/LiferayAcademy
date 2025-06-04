package com.example.taskManager.infrastructure.trainingModule.controllers;

import com.example.taskManager.application.trainingModule.dtos.UpdateTrainingModuleInputDto;
import com.example.taskManager.application.trainingModule.dtos.UpdateTrainingModuleOutputDto;
import com.example.taskManager.application.trainingModule.usecase.TrainingModuleUpdater;
import com.example.taskManager.infrastructure.trainingModule.dtos.PutTrainingModuleRequestDto;
import com.example.taskManager.infrastructure.trainingModule.dtos.PutTrainingModuleResponseDto;
import com.example.taskManager.infrastructure.trainingModule.mappers.PutTrainingModuleDtoMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/training-modules")
@CrossOrigin("http://localhost:4200")
public class TrainingModulePutController {

    private final TrainingModuleUpdater useCase;
    private final PutTrainingModuleDtoMapper dtoMapper;

    public TrainingModulePutController(
        TrainingModuleUpdater useCase,
        PutTrainingModuleDtoMapper dtoMapper
    ) {
        this.useCase = useCase;
        this.dtoMapper = dtoMapper;
    }

    @PutMapping("/{trainingModuleId}")
    public ResponseEntity<PutTrainingModuleResponseDto> updateTrainingModule(
        @PathVariable Integer trainingModuleId,
        @RequestBody PutTrainingModuleRequestDto request
    ) {
        UpdateTrainingModuleInputDto input = dtoMapper.toInput(trainingModuleId, request);
        UpdateTrainingModuleOutputDto output = useCase.execute(input);
        return ResponseEntity.ok(dtoMapper.toResponse(output));
    }
    
}