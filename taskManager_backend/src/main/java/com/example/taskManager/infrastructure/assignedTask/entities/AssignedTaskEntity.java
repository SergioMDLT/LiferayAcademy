package com.example.taskManager.infrastructure.assignedTask.entities;

import com.example.taskManager.infrastructure.task.entities.TaskEntity;
import com.example.taskManager.infrastructure.user.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "assigned_tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignedTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;

    @Column(name = "completed", nullable = false)
    private Boolean completed = false;

    @Column(name = "module_priority")
    private Integer modulePriority;

    @Column(name = "absolute_priority")
    private Integer absolutePriority;

}