package com.example.taskManager.infrastructure.user.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.taskManager.infrastructure.auth.dtos.AuthenticatedUserDTO;
import com.example.taskManager.infrastructure.auth.services.AuthenticatedUserProvider;
import com.example.taskManager.infrastructure.user.dtos.PostUserRequestDTO;
import com.example.taskManager.infrastructure.user.dtos.PostUserResponseDTO;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
public class UserPostController {

    private final AuthenticatedUserProvider authenticatedUserProvider;

    public UserPostController(
        AuthenticatedUserProvider   authenticatedUserProvider
    ) {
        this.authenticatedUserProvider =    authenticatedUserProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<PostUserResponseDTO> registerUser(@RequestBody PostUserRequestDTO requestDTO) {
    AuthenticatedUserDTO user = authenticatedUserProvider.execute(true);
    return ResponseEntity.ok(new PostUserResponseDTO(user.getUserId(), requestDTO.getEmail(), user.getRole()));
    }

}