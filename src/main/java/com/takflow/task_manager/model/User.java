package com.takflow.task_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.takflow.task_manager.model.enums.IsActive;
import com.takflow.task_manager.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties({"projects"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private IsActive isActive = IsActive.ACTIVE;

    @OneToMany
    @JoinColumn(name = "user_id")
    //@JsonManagedReference
    @ToString.Exclude
    private List<UserProject> projects = new ArrayList<>();


}
