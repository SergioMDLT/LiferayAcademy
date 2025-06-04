package com.example.taskManager.infrastructure.trainingModule.adapters;

import com.example.taskManager.domain.trainingModule.interfaces.TrainingModuleRepositoryPort;
import com.example.taskManager.domain.trainingModule.models.TrainingModule;
import com.example.taskManager.infrastructure.trainingModule.entities.TrainingModuleEntity;
import com.example.taskManager.infrastructure.trainingModule.mappers.TrainingModuleEntityMapper;
import com.example.taskManager.infrastructure.trainingModule.repositories.TrainingModuleRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class TrainingModuleRepositoryAdapter implements TrainingModuleRepositoryPort {

    private final TrainingModuleRepository trainingModuleRepository;
    private final TrainingModuleEntityMapper trainingModuleEntityMapper;

    public TrainingModuleRepositoryAdapter(
        TrainingModuleRepository trainingModuleRepository,
        TrainingModuleEntityMapper trainingModuleEntityMapper
    ) {
        this.trainingModuleRepository = trainingModuleRepository;
        this.trainingModuleEntityMapper = trainingModuleEntityMapper;
    }

    @Override
    public Optional<TrainingModule> findById(Integer id) {
        return trainingModuleRepository.findById(id)
            .map(trainingModuleEntityMapper::toDomain);
    }

    @Override
    public TrainingModule save(TrainingModule trainingModule) {
        TrainingModuleEntity entity = trainingModuleEntityMapper.toEntity(trainingModule);
        TrainingModuleEntity saved = trainingModuleRepository.save(entity);
        return trainingModuleEntityMapper.toDomain(saved);
    }

    @Override
    public List<TrainingModule> findDefaultModules() {
        return trainingModuleRepository.findAll().stream()
                .map(trainingModuleEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(TrainingModule trainingModule) {
        trainingModuleRepository.deleteById(trainingModule.getId());
    }

}