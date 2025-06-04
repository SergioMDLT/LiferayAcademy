package com.example.taskManager.application.user.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.application.user.dtos.CreateUserInputDTO;
import com.example.taskManager.application.user.dtos.CreateUserOutputDTO;
import com.example.taskManager.domain.user.models.User;

@Component
public class CreateUserApplicationMapper {

    public User toDomain(CreateUserInputDTO inputDTO) {
        User user = new User();
        user.setEmail(inputDTO.getEmail());
        user.setRole("USER");
        return user;
    }

    public CreateUserOutputDTO toOutput(User user) {
        return new CreateUserOutputDTO(user.getId(), user.getEmail(), user.getRole());
    }

}