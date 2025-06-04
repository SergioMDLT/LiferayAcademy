package com.example.taskManager.infrastructure.user.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.taskManager.domain.user.interfaces.UserRepositoryPort;
import com.example.taskManager.domain.user.models.User;
import com.example.taskManager.infrastructure.user.dtos.GetUserResponseDto;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
public class UserGetController {

    private final UserRepositoryPort userRepositoryPort;

    public UserGetController(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @GetMapping
    public ResponseEntity<List<GetUserResponseDto>> getAllUsers() {
        List<User> users = userRepositoryPort.findAll();
        List<GetUserResponseDto> response = users.stream()
            .map(user -> new GetUserResponseDto(user.getId(), user.getEmail()))
            .toList();
        return ResponseEntity.ok(response);
    }
}
