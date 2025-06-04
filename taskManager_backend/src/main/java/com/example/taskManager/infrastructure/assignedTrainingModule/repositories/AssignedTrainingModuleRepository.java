package com.example.taskManager.infrastructure.assignedTrainingModule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.taskManager.infrastructure.assignedTrainingModule.entities.AssignedTrainingModuleEntity;
import java.util.List;

@Repository
public interface AssignedTrainingModuleRepository extends JpaRepository<AssignedTrainingModuleEntity, Integer> {

    List<AssignedTrainingModuleEntity> findByUser_Id(Integer userId);

    boolean existsByUser_IdAndTrainingModule_Id(Integer userId, Integer trainingModuleId);

}