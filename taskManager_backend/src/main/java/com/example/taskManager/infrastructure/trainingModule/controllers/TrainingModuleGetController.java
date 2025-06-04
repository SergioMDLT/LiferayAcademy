package com.example.taskManager.infrastructure.trainingModule.controllers;

import com.example.taskManager.application.trainingModule.dtos.GetTrainingModuleOutputDto;
import com.example.taskManager.application.trainingModule.usecase.TrainingModuleGetter;
import com.example.taskManager.application.trainingModule.usecase.TrainingModuleLister;
import com.example.taskManager.infrastructure.trainingModule.dtos.GetTrainingModuleResponseDto;
import com.example.taskManager.infrastructure.trainingModule.mappers.GetTrainingModuleDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/training-modules")
@CrossOrigin("http://localhost:4200")
public class TrainingModuleGetController {

    private final TrainingModuleLister listerUseCase;
    private final TrainingModuleGetter getterUseCase;
    private final GetTrainingModuleDtoMapper dtoMapper;

    public TrainingModuleGetController(
        TrainingModuleLister listerUseCase,
        TrainingModuleGetter getterUseCase,
        GetTrainingModuleDtoMapper dtoMapper
    ) {
        this.getterUseCase = getterUseCase;
        this.listerUseCase = listerUseCase;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTrainingModuleResponseDto> getById(@PathVariable Integer id) {
        GetTrainingModuleOutputDto output = getterUseCase.execute(id);
        return ResponseEntity.ok(dtoMapper.toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<GetTrainingModuleResponseDto>> getAllTrainingModules() {
        List<GetTrainingModuleOutputDto> output = listerUseCase.execute();
        List<GetTrainingModuleResponseDto> response = dtoMapper.toResponseList(output);
        return ResponseEntity.ok(response);
    }

}