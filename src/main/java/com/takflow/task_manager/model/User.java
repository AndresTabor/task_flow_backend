package com.takflow.task_manager.model;

import com.takflow.task_manager.model.enums.IsActive;
import com.takflow.task_manager.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserRole role = UserRole.USER;

    @Column(nullable = false)
    private IsActive isActive = IsActive.ACTIVE;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<UserBoard> boards;

}
