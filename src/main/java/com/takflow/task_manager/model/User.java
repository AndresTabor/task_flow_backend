package com.takflow.task_manager.model;

import com.takflow.task_manager.model.enums.IsActive;
import com.takflow.task_manager.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
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
    @ToString.Exclude
    private List<UserBoard> boards = new ArrayList<>();


}
