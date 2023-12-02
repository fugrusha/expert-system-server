package com.holovko.expertsystem.model.document;

import com.holovko.expertsystem.model.entity.UserType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user_info")
public class UserInfoDocument {

    @Id
    private String id;

    private UserType userType;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String address;

    private String username;

    private String password;

    private String profilePictureURL;

    private String otherProfileInformation;
}
