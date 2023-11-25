package com.holovko.expertsystem.model.dto.user;

import com.holovko.expertsystem.model.entity.UserType;

public record UserInfoInternalDTO(
        String id,
        UserType userType,
        String username,
        String password,
        String email
) { }
