package com.example.taskManager.application.assignedTask.usecase;

import com.example.taskManager.application.assignedTask.dtos.SearchAssignedTaskFiltersDto;
import com.example.taskManager.application.assignedTask.dtos.SearchAssignedTaskOutputDto;
import com.example.taskManager.application.assignedTask.mappers.SearchAssignedTaskApplicationMapper;
import com.example.taskManager.domain.assignedTask.interfaces.AssignedTaskRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AssignedTaskSearcher {

    private final AssignedTaskRepositoryPort assignedTaskRepositoryPort;
    private final SearchAssignedTaskApplicationMapper mapper;

    public AssignedTaskSearcher(
        AssignedTaskRepositoryPort assignedTaskRepositoryPort,
        SearchAssignedTaskApplicationMapper mapper
    ) {
        this.assignedTaskRepositoryPort = assignedTaskRepositoryPort;
        this.mapper = mapper;
    }

    public Page<SearchAssignedTaskOutputDto> search(SearchAssignedTaskFiltersDto filters) {
        Pageable pageable = PageRequest.of(filters.getPage(), filters.getSize(), Sort.by(Sort.Direction.ASC, filters.getSort()));
        Integer userId = filters.getUserId();
        String title = filters.getTitle();
        Boolean completed = filters.getCompleted();
        Integer moduleId = filters.getTrainingModuleId();

        if (userId == null) {
            return assignedTaskRepositoryPort
                .findAll(pageable)
                .map(mapper::toDto);
        } else if (moduleId != null && completed != null && title != null) {
            return assignedTaskRepositoryPort
                .findByUserIdAndTrainingModuleIdAndCompletedAndTaskTitle(userId, moduleId, completed, title, pageable)
                .map(mapper::toDto);
        } else if (moduleId != null && completed != null) {
            return assignedTaskRepositoryPort
                .findByUserIdAndTrainingModuleIdAndCompleted(userId, moduleId, completed, pageable)
                .map(mapper::toDto);
        } else if (moduleId != null && title != null) {
            return assignedTaskRepositoryPort
                .findByUserIdAndTrainingModuleIdAndTaskTitle(userId, moduleId, title, pageable)
                .map(mapper::toDto);
        } else if (moduleId != null) {
            return assignedTaskRepositoryPort
                .findByUserIdAndTrainingModuleId(userId, moduleId, pageable)
                .map(mapper::toDto);
        } else if (completed != null && title != null) {
            return assignedTaskRepositoryPort
                .findByUserIdAndCompletedAndTaskTitle(userId, completed, title, pageable)
                .map(mapper::toDto);
        } else if (completed != null) {
            return assignedTaskRepositoryPort
                .findByUserIdAndCompleted(userId, completed, pageable)
                .map(mapper::toDto);
        } else if (title != null) {
            return assignedTaskRepositoryPort
                .findByUserIdAndTaskTitle(userId, title, pageable)
                .map(mapper::toDto);
        } else {
            return assignedTaskRepositoryPort
                .findByUserId(userId, pageable)
                .map(mapper::toDto);
        }
    }
}