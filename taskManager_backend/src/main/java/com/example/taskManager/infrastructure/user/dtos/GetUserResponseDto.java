package com.example.taskManager.infrastructure.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserResponseDto {
    private final Integer id;
    private final String email;

}
