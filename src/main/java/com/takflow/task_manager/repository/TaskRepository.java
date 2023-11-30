package com.takflow.task_manager.repository;

import com.takflow.task_manager.dto.response.TaskDtoResponse;
import com.takflow.task_manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.assignedMember.id = :userId")
    List<Task> getTasksAssigned(@Param("userId") Long userId);
}
