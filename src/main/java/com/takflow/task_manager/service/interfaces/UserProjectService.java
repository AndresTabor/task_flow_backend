package com.takflow.task_manager.service.interfaces;

import com.takflow.task_manager.model.Project;
import com.takflow.task_manager.model.User;
import com.takflow.task_manager.model.UserProject;
import com.takflow.task_manager.model.enums.MemberRol;

import java.util.List;

public interface UserProjectService {
    UserProject addMemberToProject(Project project, User user, MemberRol role);

    List<UserProject> getMembersProject();

    void deleteMemberProject();


}
