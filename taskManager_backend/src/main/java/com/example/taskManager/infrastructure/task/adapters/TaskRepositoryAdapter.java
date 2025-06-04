package com.example.taskManager.infrastructure.task.adapters;

import com.example.taskManager.domain.task.interfaces.TaskRepositoryPort;
import com.example.taskManager.domain.task.models.Task;
import com.example.taskManager.infrastructure.task.entities.TaskEntity;
import com.example.taskManager.infrastructure.task.mappers.TaskEntityMapper;
import com.example.taskManager.infrastructure.task.repositories.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final TaskRepository taskRepository;
    private final TaskEntityMapper taskEntityMapper;

    public TaskRepositoryAdapter(
        TaskRepository taskRepository,
        TaskEntityMapper taskEntityMapper
    ) {
        this.taskRepository = taskRepository;
        this.taskEntityMapper = taskEntityMapper;
    }

    @Override
    public Task save(Task task) {
        TaskEntity entity = taskEntityMapper.toEntity(task);
        TaskEntity savedEntity = taskRepository.save(entity);
        return taskEntityMapper.toDomain(savedEntity);
    }

    @Override
    public void delete(Task task) {
        TaskEntity entity = taskEntityMapper.toEntity(task);
        taskRepository.delete(entity);
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return taskRepository.findById(id).map(taskEntityMapper::toDomain);
    }

    @Override
    public Page<Task> findByTitleContainingIgnoreCase(String title, Pageable pageable) {
        return taskRepository.findByTitleContainingIgnoreCase(title, pageable)
                .map(taskEntityMapper::toDomain);
    }

    @Override
    public Page<Task> findByTrainingModuleId(Integer moduleId, Pageable pageable) {
        return taskRepository.findByTrainingModule_Id(moduleId, pageable)
                .map(taskEntityMapper::toDomain);
    }

    @Override
    public List<Task> findByTrainingModuleId(Integer moduleId) {
        List<TaskEntity> entities = taskRepository.findByTrainingModule_Id(moduleId);
        return entities.stream()
                       .map(taskEntityMapper::toDomain)
                       .toList();
    }

    @Override
    public Page<Task> findByTitleAndTrainingModuleId(String title, Integer moduleId, Pageable pageable) {
        return taskRepository.findByTitleContainingIgnoreCaseAndTrainingModule_Id(title, moduleId, pageable)
                .map(taskEntityMapper::toDomain);
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable).map(taskEntityMapper::toDomain);
    }
    
}