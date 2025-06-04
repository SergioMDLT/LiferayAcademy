package com.example.taskManager.application.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserInputDTO {
    private final String  auth0Id;
    private final String  email;
    
}