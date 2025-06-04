package com.example.taskManager.domain.user.interfaces;

import java.util.List;
import java.util.Optional;
import com.example.taskManager.domain.user.models.User;

public interface UserRepositoryPort {

    User save(User user, String externalId);

    Optional<User> findById(Integer id);

    Optional<User> findByExternalId(String externalId);

    List<User> findAll();

}