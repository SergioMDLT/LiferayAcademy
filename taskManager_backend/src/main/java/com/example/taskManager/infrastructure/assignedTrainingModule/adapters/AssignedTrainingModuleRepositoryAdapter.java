package com.example.taskManager.infrastructure.assignedTrainingModule.adapters;

import com.example.taskManager.domain.assignedTrainingModule.interfaces.AssignedTrainingModuleRepositoryPort;
import com.example.taskManager.domain.assignedTrainingModule.models.AssignedTrainingModule;
import com.example.taskManager.infrastructure.assignedTrainingModule.entities.AssignedTrainingModuleEntity;
import com.example.taskManager.infrastructure.assignedTrainingModule.mappers.AssignedTrainingModuleEntityMapper;
import com.example.taskManager.infrastructure.assignedTrainingModule.repositories.AssignedTrainingModuleRepository;
import com.example.taskManager.infrastructure.trainingModule.repositories.TrainingModuleRepository;
import com.example.taskManager.infrastructure.user.entities.UserEntity;
import com.example.taskManager.infrastructure.user.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AssignedTrainingModuleRepositoryAdapter implements AssignedTrainingModuleRepositoryPort {

    private final AssignedTrainingModuleRepository assignedTrainingModuleRepository;
    private final AssignedTrainingModuleEntityMapper entityMapper;
    private final UserRepository userRepository;
    private final TrainingModuleRepository trainingModuleRepository;

    public AssignedTrainingModuleRepositoryAdapter(
        AssignedTrainingModuleRepository assignedTrainingModuleRepository,
        AssignedTrainingModuleEntityMapper entityMapper,
        UserRepository userRepository,
        TrainingModuleRepository trainingModuleRepository
    ) {
        this.assignedTrainingModuleRepository = assignedTrainingModuleRepository;
        this.entityMapper = entityMapper;
        this.userRepository = userRepository;
        this.trainingModuleRepository = trainingModuleRepository;
    }

    @Override
    public AssignedTrainingModule save(AssignedTrainingModule assignedTrainingModule) {
        UserEntity user = userRepository.findById(assignedTrainingModule.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + assignedTrainingModule.getUserId()));

        var trainingModule = trainingModuleRepository.findById(assignedTrainingModule.getTrainingModuleId())
            .orElseThrow(() -> new EntityNotFoundException("TrainingModule not found with id: " + assignedTrainingModule.getTrainingModuleId()));

        AssignedTrainingModuleEntity entity = entityMapper.toEntity(assignedTrainingModule, user, trainingModule);
        AssignedTrainingModuleEntity saved = assignedTrainingModuleRepository.save(entity);
        return entityMapper.toDomain(saved);
    }

    @Override
    public void delete(AssignedTrainingModule assignedTrainingModule) {
        assignedTrainingModuleRepository.deleteById(assignedTrainingModule.getId());
    }

    @Override
    public List<AssignedTrainingModule> findAll() {
        return assignedTrainingModuleRepository.findAll().stream()
            .map(entityMapper::toDomain)
            .toList();
    }

    @Override
    public List<AssignedTrainingModule> findByUserId(Integer userId) {
        return assignedTrainingModuleRepository.findByUser_Id(userId).stream()
            .map(entityMapper::toDomain)
            .toList();
    }

    @Override
    public boolean existsByUserIdAndTrainingModuleId(Integer userId, Integer trainingModuleId) {
        return assignedTrainingModuleRepository.existsByUser_IdAndTrainingModule_Id(userId, trainingModuleId);
    }

}