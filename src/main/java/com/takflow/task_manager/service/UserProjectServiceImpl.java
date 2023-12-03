package com.takflow.task_manager.service;

import com.takflow.task_manager.exception.EntityNotFoundException;
import com.takflow.task_manager.model.Project;
import com.takflow.task_manager.model.User;
import com.takflow.task_manager.model.UserProject;
import com.takflow.task_manager.model.enums.MemberRol;
import com.takflow.task_manager.repository.UserProjectRepository;
import com.takflow.task_manager.service.interfaces.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserProjectServiceImpl implements UserProjectService {
    @Autowired
    private UserProjectRepository userProjectRepository;

    @Override
    public UserProject addMemberToProject(Project project, User user, MemberRol role) {
        UserProject member = new UserProject();
        member.setProject(project);
        member.setUser(user);
        member.setMemberRol(role);
        project.getMembers().add(member);
        return userProjectRepository.save(member);
    }

    @Override
    public UserProject getMemberById(Long userId,Long projectId ) {
        return userProjectRepository.getMemberById(userId,projectId)
                .orElseThrow(() -> new EntityNotFoundException("The member is not found"));
    }

    @Override
    public MemberRol getRoleInProject(Long userId, Long projectId){

        return userProjectRepository.getRole(userId,projectId);
    }

    @Override
    public void removeMemberToProject(Long userId, Long projectId) {
        UserProject userToRemove = userProjectRepository.getMemberById(userId,projectId)
                .orElseThrow(() -> new EntityNotFoundException("The member is not found"));
        userProjectRepository.delete(userToRemove);
    }
}
