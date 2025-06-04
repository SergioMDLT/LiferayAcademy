package com.example.taskManager.infrastructure.assignedTask.controllers;

import com.example.taskManager.application.assignedTask.dtos.GetAssignedTaskOutputDto;
import com.example.taskManager.application.assignedTask.dtos.SearchAssignedTaskFiltersDto;
import com.example.taskManager.application.assignedTask.usecase.AssignedTaskGetter;
import com.example.taskManager.application.assignedTask.usecase.AssignedTaskSearcher;
import com.example.taskManager.infrastructure.assignedTask.dtos.GetAssignedTaskResponseDto;
import com.example.taskManager.infrastructure.assignedTask.dtos.SearchAssignedTaskResponseDto;
import com.example.taskManager.infrastructure.assignedTask.mappers.GetAssignedTaskDtoMapper;
import com.example.taskManager.infrastructure.assignedTask.mappers.SearchAssignedTaskDtoMapper;
import com.example.taskManager.infrastructure.auth.services.AuthenticatedUserProvider;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assigned-tasks")
@CrossOrigin("http://localhost:4200")
public class AssignedTaskGetController {

    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final AssignedTaskGetter assignedTaskGetter;
    private final AssignedTaskSearcher assignedTaskSearcher;
    private final SearchAssignedTaskDtoMapper searchMapper;
    private final GetAssignedTaskDtoMapper getMapper;

    public AssignedTaskGetController(
        AuthenticatedUserProvider authenticatedUserProvider,
        AssignedTaskGetter assignedTaskGetter,
        AssignedTaskSearcher assignedTaskSearcher,
        SearchAssignedTaskDtoMapper searchMapper,
        GetAssignedTaskDtoMapper getMapper
    ) {
        this.authenticatedUserProvider = authenticatedUserProvider;
        this.assignedTaskGetter = assignedTaskGetter;
        this.assignedTaskSearcher = assignedTaskSearcher;
        this.searchMapper = searchMapper;
        this.getMapper = getMapper;
    }

    @GetMapping
    public ResponseEntity<Page<SearchAssignedTaskResponseDto>> searchAssignedTasks(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) Boolean completed,
        @RequestParam(required = false) Integer trainingModuleId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sort,
        @RequestParam(defaultValue = "false") boolean admin
    ) {
        Integer userId = admin ? null : authenticatedUserProvider.execute(true).getUserId();
        SearchAssignedTaskFiltersDto filters = new SearchAssignedTaskFiltersDto(userId, title, completed, trainingModuleId, page, size, sort);
        Page<SearchAssignedTaskResponseDto> response = assignedTaskSearcher.search(filters).map(searchMapper::toResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAssignedTaskResponseDto> getAssignedTaskById(@PathVariable Integer id) {
        Integer userId = authenticatedUserProvider.execute(true).getUserId();
        GetAssignedTaskOutputDto outputDto = assignedTaskGetter.execute(id, userId);
        return ResponseEntity.ok(getMapper.toResponse(outputDto));
    }


}