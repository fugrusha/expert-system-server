package com.holovko.expertsystem.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Table(name = "user_info")
public class UserInfoEntity {

    @Id
    @GeneratedValue(generator = "custom-generator")
    @GenericGenerator(name = "custom-generator", strategy = "com.holovko.expertsystem.model.entity.generator.CustomStringIdGenerator")
    private String id;

    @Enumerated(EnumType.STRING)
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

//    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
//    private BuyerEntity buyer;
//
//    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
//    private SellerEntity seller;

}
