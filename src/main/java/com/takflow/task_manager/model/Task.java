package com.takflow.task_manager.model;

import com.takflow.task_manager.model.enums.TaskState;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long boarId;
    private String title;
    private String description;
    private TaskState state;



}
