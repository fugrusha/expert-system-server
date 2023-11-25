package com.holovko.expertsystem.model.dto.user;

import lombok.Data;

@Data
public class UserLoginDTO {

    private String username;

    private char[] password;

}
