package com.holovko.expertsystem.model.dto.seller;

import lombok.Data;

@Data
public class SellerUpdateDTO {

    private String password;

    private String profilePictureURL;

    private String otherProfileInformation;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String address;
}
