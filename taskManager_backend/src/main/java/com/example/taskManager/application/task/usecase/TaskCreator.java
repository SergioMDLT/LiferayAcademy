package com.example.taskManager.application.task.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.taskManager.application.task.dtos.CreateTaskInputDto;
import com.example.taskManager.application.task.dtos.CreateTaskOutputDto;
import com.example.taskManager.application.task.mappers.CreateTaskApplicationMapper;
import com.example.taskManager.domain.task.interfaces.TaskRepositoryPort;
import com.example.taskManager.domain.task.models.Task;
import com.example.taskManager.domain.trainingModule.interfaces.TrainingModuleRepositoryPort;

@Service
public class TaskCreator {

    private final CreateTaskApplicationMapper   mapper;
    private final TaskRepositoryPort            taskRepositoryPort;
    private final TrainingModuleRepositoryPort  trainingModuleRepositoryPort;

    public TaskCreator(
        CreateTaskApplicationMapper     mapper,
        TaskRepositoryPort              taskRepositoryPort,
        TrainingModuleRepositoryPort    trainingModuleRepositoryPort
    ) {
        this.mapper =                       mapper;
        this.taskRepositoryPort =           taskRepositoryPort;
        this.trainingModuleRepositoryPort = trainingModuleRepositoryPort;
    }

    @Transactional
    public CreateTaskOutputDto execute(CreateTaskInputDto inputDTO) { 
        if (!trainingModuleRepositoryPort.findById(inputDTO.getTrainingModuleId()).isPresent()) {
            throw new IllegalArgumentException("El módulo de formación no existe.");
        }

        Task task = mapper.toDomain(inputDTO);
        Task saved = taskRepositoryPort.save(task);
        return mapper.toDTO(saved);
    }

}