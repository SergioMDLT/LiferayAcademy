package com.example.taskManager.infrastructure.assignedTask.repositories;

import com.example.taskManager.infrastructure.assignedTask.entities.AssignedTaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface AssignedTaskRepository extends JpaRepository<AssignedTaskEntity, Integer> {

    @Override
    @EntityGraph(attributePaths = { "user", "task" })
    Page<AssignedTaskEntity> findAll(Pageable pageable);

    Optional<AssignedTaskEntity> findByUser_Auth0IdAndTask_Id(String auth0Id, Integer taskId);

    Page<AssignedTaskEntity> findByUser_Auth0Id(String auth0Id, Pageable pageable);

    Page<AssignedTaskEntity> findByUser_Auth0IdAndCompleted(String auth0Id, Boolean completed, Pageable pageable);

    boolean existsByTask_Id(Integer taskId);

    @Query("""
        SELECT MAX(at.modulePriority)
        FROM AssignedTaskEntity at
        JOIN at.task t
        WHERE at.user.auth0Id = :auth0Id AND t.trainingModule.id = :trainingModuleId
    """)
    Optional<Integer> findMaxModulePriority(
        @Param("auth0Id") String auth0Id,
        @Param("trainingModuleId") Integer trainingModuleId
    );

    @Query("""
        SELECT COUNT(at) > 0
        FROM AssignedTaskEntity at
        JOIN at.task t
        WHERE at.user.auth0Id = :auth0Id AND t.trainingModule.id = :trainingModuleId AND at.modulePriority = :priority
    """)
    boolean existsWithModulePriority(
        @Param("auth0Id") String auth0Id,
        @Param("trainingModuleId") Integer trainingModuleId,
        @Param("priority") Integer priority
    );

    @Modifying
    @Query("""
        UPDATE AssignedTaskEntity at SET at.modulePriority = at.modulePriority + 1
        WHERE at.user.auth0Id = :auth0Id
        AND at.task.trainingModule.id = :trainingModuleId
        AND at.modulePriority >= :from
    """)
    void incrementModulePriorities(
        @Param("auth0Id") String auth0Id,
        @Param("trainingModuleId") Integer trainingModuleId,
        @Param("from") int from
    );
    
    @Modifying
    @Query("""
        UPDATE AssignedTaskEntity at SET at.modulePriority = at.modulePriority - 1
        WHERE at.user.auth0Id = :auth0Id
        AND at.task.trainingModule.id = :trainingModuleId
        AND at.modulePriority >= :from
    """)
    void decrementModulePriorities(
        @Param("auth0Id") String auth0Id,
        @Param("trainingModuleId") Integer trainingModuleId,
        @Param("from") int from
    );

    @Modifying
    @Query("""
        UPDATE AssignedTaskEntity at SET at.modulePriority = at.modulePriority - 1
        WHERE at.user.auth0Id = :auth0Id AND at.modulePriority > :removedPriority
        AND at.task.trainingModule.id = :trainingModuleId
    """)
    void reduceModulePrioritiesAfterCompletion(
        @Param("auth0Id") String auth0Id,
        @Param("trainingModuleId") Integer trainingModuleId,
        @Param("removedPriority") Integer removedPriority
    );

    @Query("""
        SELECT MAX(at.absolutePriority)
        FROM AssignedTaskEntity at
        WHERE at.user.auth0Id = :auth0Id
    """)
    Optional<Integer> findMaxAbsolutePriority(@Param("auth0Id") String auth0Id);

    @Modifying
    @Query("""
        UPDATE AssignedTaskEntity at SET at.absolutePriority = at.absolutePriority + 1
        WHERE at.user.auth0Id = :auth0Id AND at.absolutePriority >= :from
    """)
    void incrementAbsolutePriorities(
        @Param("auth0Id") String auth0Id,
        @Param("from") int from
    );

    @Modifying
    @Query("""
        UPDATE AssignedTaskEntity at SET at.absolutePriority = at.absolutePriority - 1
        WHERE at.user.auth0Id = :auth0Id AND at.absolutePriority >= :from
    """)
    void decrementAbsolutePriorities(
        @Param("auth0Id") String auth0Id,
        @Param("from") int from
    );

    @Modifying
    @Query("""
        UPDATE AssignedTaskEntity at SET at.absolutePriority = at.absolutePriority - 1
        WHERE at.user.auth0Id = :auth0Id AND at.absolutePriority > :removedPriority
    """)
    void reduceAbsolutePrioritiesAfterCompletion(
        @Param("auth0Id") String auth0Id,
        @Param("removedPriority") Integer removedPriority
    );

    @Query("""
        SELECT at FROM AssignedTaskEntity at
        JOIN at.task t
        WHERE at.user.auth0Id = :auth0Id AND t.trainingModule.id = :moduleId
        ORDER BY at.modulePriority ASC
    """)
    Page<AssignedTaskEntity> findByUserAndTrainingModule(
        @Param("auth0Id") String auth0Id,
        @Param("moduleId") Integer trainingModuleId,
        Pageable pageable
    );

    @Query("""
        SELECT at FROM AssignedTaskEntity at
        JOIN at.task t
        WHERE at.user.auth0Id = :auth0Id AND t.trainingModule.id = :moduleId AND at.completed = :completed
        ORDER BY at.modulePriority ASC
    """)
    Page<AssignedTaskEntity> findByUserAndTrainingModuleAndCompleted(
        @Param("auth0Id") String auth0Id,
        @Param("moduleId") Integer moduleId,
        @Param("completed") Boolean completed,
        Pageable pageable
    );

    @Query("""
        SELECT at FROM AssignedTaskEntity at
        JOIN at.task t
        WHERE at.user.auth0Id = :auth0Id AND t.trainingModule.id = :moduleId
        AND LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))
    """)
    Page<AssignedTaskEntity> findByUserAndModuleAndTaskTitle(
        @Param("auth0Id") String auth0Id,
        @Param("moduleId") Integer moduleId,
        @Param("title") String title,
        Pageable pageable
    );

    @Query("""
        SELECT at FROM AssignedTaskEntity at
        JOIN at.task t
        WHERE at.user.auth0Id = :auth0Id AND t.trainingModule.id = :moduleId AND at.completed = :completed
        AND LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))
    """)
    Page<AssignedTaskEntity> findByUserAndModuleAndCompletedAndTaskTitle(
        @Param("auth0Id") String auth0Id,
        @Param("moduleId") Integer moduleId,
        @Param("completed") Boolean completed,
        @Param("title") String title,
        Pageable pageable
    );

    @Query("""
        SELECT at FROM AssignedTaskEntity at
        JOIN at.task t
        WHERE at.user.auth0Id = :auth0Id AND LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))
    """)
    Page<AssignedTaskEntity> findByUser_Auth0IdAndTask_TitleContainingIgnoreCase(
        @Param("auth0Id") String auth0Id,
        @Param("title") String title,
        Pageable pageable
    );

    @Query("""
        SELECT at FROM AssignedTaskEntity at
        JOIN at.task t
        WHERE at.user.auth0Id = :auth0Id AND at.completed = :completed
        AND LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))
    """)
    Page<AssignedTaskEntity> findByUser_Auth0IdAndCompletedAndTask_TitleContainingIgnoreCase(
        @Param("auth0Id") String auth0Id,
        @Param("completed") Boolean completed,
        @Param("title") String title,
        Pageable pageable
    );

}