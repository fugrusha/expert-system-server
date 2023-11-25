package com.holovko.expertsystem.model.dto.user;

import com.holovko.expertsystem.model.entity.UserType;
import lombok.Data;

@Data
public class UserRegisterDTO {

    private String username;

    private char[] password;

    private String email;

    private UserType userType;

}
