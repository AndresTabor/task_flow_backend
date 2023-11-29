package com.takflow.task_manager.dto.response;

import com.takflow.task_manager.model.enums.MemberRol;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserProjectDtoResponse {

    @NonNull
    private MemberDto user;

    @NonNull
    private MemberRol memberRol;


}
