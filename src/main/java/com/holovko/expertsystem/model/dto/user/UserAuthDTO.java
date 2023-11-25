package com.holovko.expertsystem.model.dto.user;

import com.holovko.expertsystem.model.entity.UserType;
import lombok.Data;

@Data
public class UserAuthDTO {

    private String id;
    private UserType userType;
    private String username;
    private String token;
}
