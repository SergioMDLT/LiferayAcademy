package com.example.taskManager.infrastructure.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUserResponseDTO {
    private final Integer id;
    private final String  email;
    private final String  role;

}