package com.takflow.task_manager.repository;

import com.takflow.task_manager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
