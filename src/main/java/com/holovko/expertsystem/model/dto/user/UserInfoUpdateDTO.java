package com.holovko.expertsystem.model.dto.user;

import lombok.Data;

@Data
public class UserInfoUpdateDTO {

    private String profilePictureURL;

    private String otherProfileInformation;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String address;
}
