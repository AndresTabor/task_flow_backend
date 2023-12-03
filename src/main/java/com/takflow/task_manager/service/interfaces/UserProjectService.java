package com.takflow.task_manager.service.interfaces;

import com.takflow.task_manager.model.Project;
import com.takflow.task_manager.model.User;
import com.takflow.task_manager.model.UserProject;
import com.takflow.task_manager.model.enums.MemberRol;



public interface UserProjectService {
    UserProject addMemberToProject(Project project, User user, MemberRol role);

    UserProject getMemberById(Long userId, Long projectId);

    MemberRol getRoleInProject(Long userId, Long projectId);

    void removeMemberToProject(Long userId, Long projectId);


}
