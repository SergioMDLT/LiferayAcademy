package com.example.taskManager.infrastructure.user.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.domain.user.models.User;
import com.example.taskManager.infrastructure.user.entities.UserEntity;

@Component
public class UserEntityMapper {

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;

        return new User(
            entity.getId(),
            entity.getEmail(),
            entity.getRole()
        );
    }

    public UserEntity toEntity(User user, String auth0Id) {
        if (user == null) return null;
        
        return UserEntity.builder()
            .id(user.getId())
            .auth0Id(auth0Id)
            .email(user.getEmail())
            .role(user.getRole())
            .build();
    }

}
