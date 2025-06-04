package com.example.taskManager.infrastructure.auth.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import com.example.taskManager.application.user.dtos.CreateUserInputDTO;
import com.example.taskManager.application.user.dtos.CreateUserOutputDTO;
import com.example.taskManager.application.user.usecase.UserCreator;
import com.example.taskManager.infrastructure.auth.dtos.AuthenticatedUserDTO;
import com.example.taskManager.infrastructure.user.adapters.UserRepositoryAdapter;

@Component
public class AuthenticatedUserProvider {

    private final UserRepositoryAdapter userRepository;
    private final UserCreator           userCreator;

    public AuthenticatedUserProvider(
        UserCreator userCreator,
        UserRepositoryAdapter userRepository
    ) {
        this.userCreator = userCreator;
        this.userRepository = userRepository;
    }

    public AuthenticatedUserDTO execute(boolean allowAutoRegister) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String auth0Id = jwt.getClaimAsString("sub");
        String email = jwt.getClaimAsString("email");
        
        if (email == null || email.isBlank()) {
            throw new IllegalStateException("El token JWT no contiene el email del usuario");
        }
    
        return userRepository.findByExternalId(auth0Id)
            .map(user -> new AuthenticatedUserDTO(auth0Id, user.getId(), user.getRole()))
            .orElseGet(() -> {
                if (!allowAutoRegister) {
                    return new AuthenticatedUserDTO(auth0Id, null, null);
                }
            
                CreateUserInputDTO input = new CreateUserInputDTO(auth0Id, email);
                CreateUserOutputDTO created = userCreator.execute(input);
                return new AuthenticatedUserDTO(auth0Id, created.getId(), created.getRole());
            });
    }

}