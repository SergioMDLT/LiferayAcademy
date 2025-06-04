package com.example.taskManager.infrastructure.user.mappers;

import org.springframework.stereotype.Component;

import com.example.taskManager.application.user.dtos.CreateUserInputDTO;
import com.example.taskManager.application.user.dtos.CreateUserOutputDTO;
import com.example.taskManager.infrastructure.user.dtos.PostUserRequestDTO;
import com.example.taskManager.infrastructure.user.dtos.PostUserResponseDTO;

@Component
public class PostUserDtoMapper {

    public CreateUserInputDTO toInput(PostUserRequestDTO requestDTO) {
        return new CreateUserInputDTO(requestDTO.getAuth0Id(), requestDTO.getEmail());
    }

    public PostUserResponseDTO toResponse(CreateUserOutputDTO outputDTO) {
        return new PostUserResponseDTO(outputDTO.getId(), outputDTO.getEmail(), "USER");
    }
    
}