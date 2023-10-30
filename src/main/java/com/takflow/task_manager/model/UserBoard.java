package com.takflow.task_manager.model;

import com.takflow.task_manager.model.enums.MemberRol;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_board")
public class UserBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    private MemberRol rol = MemberRol.MEMBER;

}
