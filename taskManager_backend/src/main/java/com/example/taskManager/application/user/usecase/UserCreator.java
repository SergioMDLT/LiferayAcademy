package com.example.taskManager.application.user.usecase;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.taskManager.application.user.dtos.CreateUserInputDTO;
import com.example.taskManager.application.user.dtos.CreateUserOutputDTO;
import com.example.taskManager.application.user.mappers.CreateUserApplicationMapper;
import com.example.taskManager.domain.user.interfaces.UserRepositoryPort;
import com.example.taskManager.domain.user.models.User;

@Service
public class UserCreator {

    private final CreateUserApplicationMapper userApplicationMapper;
    private final UserRepositoryPort          userRepositoryPort;

    public UserCreator(
        CreateUserApplicationMapper userApplicationMapper,
        UserRepositoryPort          userRepositoryPort
    ) {
        this.userApplicationMapper =    userApplicationMapper;
        this.userRepositoryPort =       userRepositoryPort;
    }

    public CreateUserOutputDTO execute(CreateUserInputDTO inputDTO) {
        Optional<User> existing = userRepositoryPort.findByExternalId(inputDTO.getAuth0Id());
        if (existing.isPresent()) {
            return userApplicationMapper.toOutput(existing.get());
        }
        User user = userApplicationMapper.toDomain(inputDTO);
        User saved = userRepositoryPort.save(user, inputDTO.getAuth0Id());
        return userApplicationMapper.toOutput(saved);
    }

}