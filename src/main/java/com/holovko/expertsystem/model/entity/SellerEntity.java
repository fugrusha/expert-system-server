//package com.holovko.expertsystem.model.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "seller")
//public class SellerEntity {
//
//    @Id
//    @GeneratedValue(generator = "custom-generator")
//    @GenericGenerator(name = "uuid", strategy = "com.holovko.expertsystem.model.entity.generator.CustomStringIdGenerator")
//    private String sellerId;
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private UserInfoEntity userInfo;
//}
