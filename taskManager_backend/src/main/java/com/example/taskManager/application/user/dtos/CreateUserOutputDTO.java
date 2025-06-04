package com.example.taskManager.application.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserOutputDTO {
    private final Integer id;
    private final String  email;
    private final String  role;

}