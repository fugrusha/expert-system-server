package com.holovko.expertsystem.model.dto.seller;

import com.holovko.expertsystem.model.entity.UserType;
import lombok.Data;

@Data
public class UserInfoCreateDTO {

    private UserType userType;

    private String username;

    private String password;

    private String email;
}
