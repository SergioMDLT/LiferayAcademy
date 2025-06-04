package com.example.taskManager.infrastructure.task.mappers;

import org.springframework.stereotype.Component;
import com.example.taskManager.application.task.dtos.SearchTaskInputDto;
import com.example.taskManager.application.task.dtos.SearchTaskOutputDto;
import com.example.taskManager.infrastructure.task.dtos.SearchTaskRequestDto;
import com.example.taskManager.infrastructure.task.dtos.SearchTaskResponseDto;

@Component
public class SearchTaskDtoMapper {

    public SearchTaskInputDto toInput(SearchTaskRequestDto dto) {
        SearchTaskInputDto input = new SearchTaskInputDto();
        input.setPage(dto.getPage());
        input.setSize(dto.getSize());
        input.setSort(dto.getSort());
        return input;
    }

    public SearchTaskResponseDto toResponse(SearchTaskOutputDto output) {
        return new SearchTaskResponseDto(
            output.getId(),
            output.getTitle(),
            output.getDescription(),
            output.getTrainingModuleId()
        );
    }

}