package com.example.taskManager.domain.assignedTask.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.taskManager.domain.assignedTask.models.AssignedTask;
import java.util.Optional;

public interface AssignedTaskRepositoryPort {

    AssignedTask save(AssignedTask assignedTask);

    void delete(AssignedTask assignedTask);

    Page<AssignedTask> findAll(Pageable pageable);

    Optional<AssignedTask> findById(Integer id);

    Optional<AssignedTask> findByUserIdAndTaskId(Integer userId, Integer taskId);

    Page<AssignedTask> findByUserId(Integer userId, Pageable pageable);

    Page<AssignedTask> findByUserIdAndCompleted(Integer userId, Boolean completed, Pageable pageable);

    Page<AssignedTask> findByUserIdAndTrainingModuleId(Integer userId, Integer trainingModuleId, Pageable pageable);

    Page<AssignedTask> findByUserIdAndTrainingModuleIdAndCompleted(Integer userId, Integer trainingModuleId, Boolean completed, Pageable pageable);

    Page<AssignedTask> findByUserIdAndTrainingModuleIdAndCompletedAndTaskTitle(Integer userId, Integer moduleId, Boolean completed, String title, Pageable pageable);

    Page<AssignedTask> findByUserIdAndTrainingModuleIdAndTaskTitle(Integer userId, Integer moduleId, String title, Pageable pageable);

    Page<AssignedTask> findByUserIdAndCompletedAndTaskTitle(Integer userId, Boolean completed, String title, Pageable pageable);

    Page<AssignedTask> findByUserIdAndTaskTitle(Integer userId, String title, Pageable pageable);

    Optional<Integer> findMaxModulePriority(Integer userId, Integer trainingModuleId);

    boolean existsWithPriority(Integer userId, Integer trainingModuleId, Integer priority);

    void incrementPriorities(Integer userId, Integer trainingModuleId, int from);

    void decrementPriorities(Integer userId, Integer trainingModuleId, int from);

    void reducePrioritiesAfterCompletion(Integer userId, Integer trainingModuleId, Integer removedPriority);

    Optional<Integer> findMaxAbsolutePriority(Integer userId);

    void incrementAbsolutePriorities(Integer userId, int from);

    void decrementAbsolutePriorities(Integer userId, int from);

    void reduceAbsolutePrioritiesAfterCompletion(Integer userId, Integer removedPriority);

    boolean existsByTaskId(Integer taskId);
}