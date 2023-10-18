package com.holovko.expertsystem.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("buyers")
public class BuyerDocument {

    @Id
    private String id;

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
