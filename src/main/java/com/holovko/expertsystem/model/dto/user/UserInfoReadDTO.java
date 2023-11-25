package com.holovko.expertsystem.model.dto.user;

import com.holovko.expertsystem.model.entity.UserType;
import lombok.Data;

@Data
public class UserInfoReadDTO {

    private String id;

    private UserType userType;

    private String username;

    private String profilePictureURL;

    private String otherProfileInformation;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String address;
}
