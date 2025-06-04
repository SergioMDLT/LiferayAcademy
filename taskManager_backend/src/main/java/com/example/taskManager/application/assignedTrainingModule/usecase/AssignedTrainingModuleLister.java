package com.example.taskManager.application.assignedTrainingModule.usecase;

import com.example.taskManager.application.assignedTrainingModule.dtos.GetAllAssignedTrainingModuleOutputDto;
import com.example.taskManager.application.assignedTrainingModule.mappers.GetAssignedTrainingModuleApplicationMapper;
import com.example.taskManager.domain.assignedTrainingModule.interfaces.AssignedTrainingModuleRepositoryPort;
import com.example.taskManager.domain.assignedTrainingModule.models.AssignedTrainingModule;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssignedTrainingModuleLister {

    private final AssignedTrainingModuleRepositoryPort repository;
    private final GetAssignedTrainingModuleApplicationMapper mapper;

    public AssignedTrainingModuleLister(
        AssignedTrainingModuleRepositoryPort repository,
        GetAssignedTrainingModuleApplicationMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<GetAllAssignedTrainingModuleOutputDto> execute() {
        List<AssignedTrainingModule> modules = repository.findAll();
        return mapper.toOutputList(modules);
    }

}