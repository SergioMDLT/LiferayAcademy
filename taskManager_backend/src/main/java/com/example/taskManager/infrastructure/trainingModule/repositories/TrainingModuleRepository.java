package com.example.taskManager.infrastructure.trainingModule.repositories;

import com.example.taskManager.infrastructure.trainingModule.entities.TrainingModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingModuleRepository extends JpaRepository<TrainingModuleEntity, Integer> {

}