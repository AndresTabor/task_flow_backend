package com.takflow.task_manager.repository;

import com.takflow.task_manager.model.UserProject;
import com.takflow.task_manager.model.enums.MemberRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject,Long> {
    @Query("SELECT u.memberRol FROM UserProject u where u.user.id = :userId AND u.project.id = :projectId")
    MemberRol getRole(Long userId, Long projectId);

    @Query("SELECT u FROM UserProject u where u.user.id = :userId AND u.project.id = :projectId")
    Optional<UserProject> getMemberById(Long userId, Long projectId);

}
