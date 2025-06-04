package com.example.taskManager.infrastructure.task.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.taskManager.infrastructure.task.entities.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer>{

    List<TaskEntity> findByTrainingModule_Id(Integer trainingModuleId);

    Page<TaskEntity> findByTrainingModule_Id(Integer trainingModuleId, Pageable pageable);

    Page<TaskEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    
    Page<TaskEntity> findByTitleContainingIgnoreCaseAndTrainingModule_Id(String title, Integer trainingModuleId, Pageable pageable);

    Page<TaskEntity> findByTrainingModuleId(Integer trainingModuleId, Pageable pageable);

}