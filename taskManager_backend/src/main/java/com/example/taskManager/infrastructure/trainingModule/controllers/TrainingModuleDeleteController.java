package com.example.taskManager.infrastructure.trainingModule.controllers;

import com.example.taskManager.application.trainingModule.dtos.DeleteTrainingModuleInputDto;
import com.example.taskManager.application.trainingModule.usecase.TrainingModuleDeleter;
import com.example.taskManager.infrastructure.trainingModule.mappers.DeleteTrainingModuleDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/training-modules")
@CrossOrigin("http://localhost:4200")
public class TrainingModuleDeleteController {

    private final TrainingModuleDeleter useCase;
    private final DeleteTrainingModuleDtoMapper dtoMapper;

    public TrainingModuleDeleteController(
        TrainingModuleDeleter useCase,
        DeleteTrainingModuleDtoMapper dtoMapper
    ) {
        this.useCase = useCase;
        this.dtoMapper = dtoMapper;
    }

    @DeleteMapping("/{trainingModuleId}")
    public ResponseEntity<Void> delete(@PathVariable Integer trainingModuleId) {
        DeleteTrainingModuleInputDto input = dtoMapper.toInput(trainingModuleId);
        useCase.execute(input);
        return ResponseEntity.noContent().build();
    }
    
}