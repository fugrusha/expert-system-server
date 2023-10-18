package com.holovko.expertsystem.model.dto.seller;

import com.holovko.expertsystem.model.entity.UserType;
import lombok.Data;

@Data
public class SellerReadDTO {

    private String id;

    private UserType userType;

    private String username;

    private String password;

    private String profilePictureURL;

    private String otherProfileInformation;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String address;
}
