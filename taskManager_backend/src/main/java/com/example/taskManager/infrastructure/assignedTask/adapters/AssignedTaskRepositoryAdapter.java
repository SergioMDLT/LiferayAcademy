package com.example.taskManager.infrastructure.assignedTask.adapters;

import com.example.taskManager.domain.assignedTask.interfaces.AssignedTaskRepositoryPort;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import com.example.taskManager.infrastructure.assignedTask.mappers.AssignedTaskEntityMapper;
import com.example.taskManager.infrastructure.assignedTask.repositories.AssignedTaskRepository;
import com.example.taskManager.infrastructure.user.entities.UserEntity;
import com.example.taskManager.infrastructure.user.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class AssignedTaskRepositoryAdapter implements AssignedTaskRepositoryPort {

    private final AssignedTaskRepository repository;
    private final AssignedTaskEntityMapper mapper;
    private final UserRepository userRepository;

    public AssignedTaskRepositoryAdapter(
        AssignedTaskRepository repository,
        AssignedTaskEntityMapper mapper,
        UserRepository userRepository
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    private String resolveAuth0Id(Integer userId) {
        return userRepository.findById(userId)
            .map(UserEntity::getAuth0Id)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

    @Override
    public AssignedTask save(AssignedTask assignedTask) {
        return mapper.toDomain(repository.save(mapper.toEntity(assignedTask)));
    }

    @Override
    public void delete(AssignedTask assignedTask) {
        repository.delete(mapper.toEntity(assignedTask));
    }

    @Override
    public Page<AssignedTask> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDomain);
    }

    @Override
    public Optional<AssignedTask> findById(Integer id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<AssignedTask> findByUserIdAndTaskId(Integer userId, Integer taskId) {
        return repository.findByUser_Auth0IdAndTask_Id(resolveAuth0Id(userId), taskId)
                .map(mapper::toDomain);
    }

    @Override
    public Page<AssignedTask> findByUserId(Integer userId, Pageable pageable) {
        return repository.findByUser_Auth0Id(resolveAuth0Id(userId), pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<AssignedTask> findByUserIdAndCompleted(Integer userId, Boolean completed, Pageable pageable) {
        return repository.findByUser_Auth0IdAndCompleted(resolveAuth0Id(userId), completed, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<AssignedTask> findByUserIdAndTaskTitle(Integer userId, String title, Pageable pageable) {
        return repository.findByUser_Auth0IdAndTask_TitleContainingIgnoreCase(resolveAuth0Id(userId), title, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<AssignedTask> findByUserIdAndCompletedAndTaskTitle(Integer userId, Boolean completed, String title, Pageable pageable) {
        return repository.findByUser_Auth0IdAndCompletedAndTask_TitleContainingIgnoreCase(resolveAuth0Id(userId), completed, title, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<AssignedTask> findByUserIdAndTrainingModuleId(Integer userId, Integer moduleId, Pageable pageable) {
        return repository.findByUserAndTrainingModule(resolveAuth0Id(userId), moduleId, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<AssignedTask> findByUserIdAndTrainingModuleIdAndCompleted(Integer userId, Integer moduleId, Boolean completed, Pageable pageable) {
        return repository.findByUserAndTrainingModuleAndCompleted(resolveAuth0Id(userId), moduleId, completed, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<AssignedTask> findByUserIdAndTrainingModuleIdAndTaskTitle(Integer userId, Integer moduleId, String title, Pageable pageable) {
        return repository.findByUserAndModuleAndTaskTitle(resolveAuth0Id(userId), moduleId, title, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<AssignedTask> findByUserIdAndTrainingModuleIdAndCompletedAndTaskTitle(Integer userId, Integer moduleId, Boolean completed, String title, Pageable pageable) {
        return repository.findByUserAndModuleAndCompletedAndTaskTitle(resolveAuth0Id(userId), moduleId, completed, title, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Integer> findMaxModulePriority(Integer userId, Integer trainingModuleId) {
        return repository.findMaxModulePriority(resolveAuth0Id(userId), trainingModuleId);
    }

    @Override
    public boolean existsWithPriority(Integer userId, Integer trainingModuleId, Integer priority) {
        return repository.existsWithModulePriority(resolveAuth0Id(userId), trainingModuleId, priority);
    }

    @Override
    public void incrementPriorities(Integer userId, Integer trainingModuleId, int from) {
        repository.incrementModulePriorities(resolveAuth0Id(userId), trainingModuleId, from);
    }

    @Override
    public void decrementPriorities(Integer userId, Integer trainingModuleId, int from) {
        repository.decrementModulePriorities(resolveAuth0Id(userId), trainingModuleId, from);
    }

    @Override
    public void reducePrioritiesAfterCompletion(Integer userId, Integer trainingModuleId, Integer removedPriority) {
        repository.reduceModulePrioritiesAfterCompletion(resolveAuth0Id(userId), trainingModuleId, removedPriority);
    }

    @Override
    public Optional<Integer> findMaxAbsolutePriority(Integer userId) {
        return repository.findMaxAbsolutePriority(resolveAuth0Id(userId));
    }

    @Override
    public void incrementAbsolutePriorities(Integer userId, int from) {
        repository.incrementAbsolutePriorities(resolveAuth0Id(userId), from);
    }

    @Override
    public void decrementAbsolutePriorities(Integer userId, int from) {
        repository.decrementAbsolutePriorities(resolveAuth0Id(userId), from);
    }

    @Override
    public void reduceAbsolutePrioritiesAfterCompletion(Integer userId, Integer removedPriority) {
        repository.reduceAbsolutePrioritiesAfterCompletion(resolveAuth0Id(userId), removedPriority);
    }

    @Override
    public boolean existsByTaskId(Integer taskId) {
        return repository.existsByTask_Id(taskId);
    }
    
}