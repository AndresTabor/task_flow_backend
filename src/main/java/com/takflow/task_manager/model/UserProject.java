package com.takflow.task_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.takflow.task_manager.model.enums.MemberRol;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_project")
//@JsonIgnoreProperties({"user", "project"})
@JsonIgnoreProperties({"project"})
public class UserProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    private MemberRol memberRol = MemberRol.MEMBER;

}
