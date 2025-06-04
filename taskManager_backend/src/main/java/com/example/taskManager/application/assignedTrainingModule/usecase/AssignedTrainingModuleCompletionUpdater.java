package com.example.taskManager.application.assignedTrainingModule.usecase;

import com.example.taskManager.application.assignedTrainingModule.dtos.UpdateAssignedTrainingModuleCompletionInputDto;
import com.example.taskManager.application.assignedTrainingModule.dtos.UpdateAssignedTrainingModuleCompletionOutputDto;
import com.example.taskManager.application.assignedTrainingModule.mappers.UpdateAssignedTrainingModuleCompletionApplicationMapper;
import com.example.taskManager.domain.assignedTrainingModule.interfaces.AssignedTrainingModuleRepositoryPort;
import com.example.taskManager.domain.assignedTrainingModule.models.AssignedTrainingModule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AssignedTrainingModuleCompletionUpdater {

    private final AssignedTrainingModuleRepositoryPort repository;
    private final UpdateAssignedTrainingModuleCompletionApplicationMapper mapper;

    public AssignedTrainingModuleCompletionUpdater(
        AssignedTrainingModuleRepositoryPort repository,
        UpdateAssignedTrainingModuleCompletionApplicationMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public UpdateAssignedTrainingModuleCompletionOutputDto execute(UpdateAssignedTrainingModuleCompletionInputDto input) {
        List<AssignedTrainingModule> assignedList = repository.findByUserId(input.getUserId());

        AssignedTrainingModule module = assignedList.stream()
            .filter(m -> m.getId().equals(input.getAssignedTrainingModuleId()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("El módulo no pertenece a este usuario."));

        AssignedTrainingModule updated = new AssignedTrainingModule(
            module.getId(),
            module.getUserId(),
            module.getTrainingModuleId(),
            input.getCompleted()
        );

        AssignedTrainingModule saved = repository.save(updated);
        return mapper.toDto(saved);
    }

}