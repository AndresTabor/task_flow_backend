package com.takflow.task_manager.repository;


import com.takflow.task_manager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("SELECT p.id as id, p.name as name FROM Project p " +
            "JOIN p.members m WHERE m.user.id = :userId AND " +
            "p.isActive = com.takflow.task_manager.model.enums.IsActive.ACTIVE")
    List<ProjectSummaryProjection> findParticipatingProjects(@Param("userId") Long userId);


}
