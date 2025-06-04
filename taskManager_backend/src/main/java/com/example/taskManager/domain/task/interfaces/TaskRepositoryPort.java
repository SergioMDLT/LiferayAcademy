package com.example.taskManager.domain.task.interfaces;

import com.example.taskManager.domain.task.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface TaskRepositoryPort {

    Optional<Task> findById(Integer id);

    Page<Task> findAll(Pageable pageable);

    Page<Task> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Task> findByTrainingModuleId(Integer trainingModuleId, Pageable pageable);

    List<Task> findByTrainingModuleId(Integer trainingModuleId);

    Page<Task> findByTitleAndTrainingModuleId(String title, Integer trainingModuleId, Pageable pageable);

    Task save(Task task);

    void delete(Task task);

}