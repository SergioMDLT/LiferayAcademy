package com.example.taskManager.infrastructure.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUserRequestDTO {
    private String auth0Id;
    private String email;
    
}