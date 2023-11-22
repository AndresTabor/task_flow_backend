package com.takflow.task_manager.service;

import com.takflow.task_manager.dto.response.UserDtoResponse;
import com.takflow.task_manager.model.Project;
import com.takflow.task_manager.model.User;
import com.takflow.task_manager.model.UserProject;
import com.takflow.task_manager.model.enums.MemberRol;
import com.takflow.task_manager.repository.UserProjectRepository;
import com.takflow.task_manager.service.interfaces.UserProjectService;
import com.takflow.task_manager.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProjectServiceImpl implements UserProjectService {
    @Autowired
    private UserProjectRepository userProjectRepository;

    @Autowired
    private UserService userService;

    @Override
    public UserProject addMemberToProject(Project project, User user, MemberRol role) {
        UserProject member = new UserProject();
        member.setProject(project);
        member.setUser(user);
        member.setMemberRol(role);
        return userProjectRepository.save(member);
    }

    @Override
    public List<UserProject> getMembersProject() {
        return null;
    }

    @Override
    public void deleteMemberProject() {

    }
}
