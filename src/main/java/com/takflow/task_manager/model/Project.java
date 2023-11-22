package com.takflow.task_manager.model;

import com.takflow.task_manager.model.enums.IsActive;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private IsActive isActive = IsActive.ACTIVE;

    @OneToMany()
    @JoinColumn(name = "project_id")
    private List<Task> tasks = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "project_id")
    private List<UserProject> members = new ArrayList<>();

}
