package com.takflow.task_manager.dto.response;

import lombok.Data;

@Data
public class ProjectSummaryDto {
    private Long id;
    private String name;

    public ProjectSummaryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
