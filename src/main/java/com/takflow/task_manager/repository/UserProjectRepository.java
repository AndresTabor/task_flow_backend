package com.takflow.task_manager.repository;

import com.takflow.task_manager.model.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject,Long> {
}
