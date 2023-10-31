package com.takflow.task_manager.model;

import com.takflow.task_manager.model.enums.IsActive;
import com.takflow.task_manager.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

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
    private List<UserBoard> boards = new ArrayList<>();

}
