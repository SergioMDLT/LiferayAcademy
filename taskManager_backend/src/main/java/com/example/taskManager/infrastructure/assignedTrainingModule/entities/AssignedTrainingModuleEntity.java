package com.example.taskManager.infrastructure.assignedTrainingModule.entities;

import com.example.taskManager.infrastructure.user.entities.UserEntity;
import com.example.taskManager.infrastructure.trainingModule.entities.TrainingModuleEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assigned_training_modules")
public class AssignedTrainingModuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "training_module_id")
    private TrainingModuleEntity trainingModule;

    @Column(name = "completed", nullable = false)
    private boolean completed = false;

}