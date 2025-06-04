package com.example.taskManager.infrastructure.user.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskManager.infrastructure.user.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByAuth0Id(String auth0Id);

    List<UserEntity> findAllByOrderByEmailAsc();

}