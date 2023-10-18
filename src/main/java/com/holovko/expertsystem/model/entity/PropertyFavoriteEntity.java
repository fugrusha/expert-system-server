package com.holovko.expertsystem.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Table(name = "property_favorite")
public class PropertyFavoriteEntity {

    @Id
    @GeneratedValue(generator = "custom-generator")
    @GenericGenerator(name = "custom-generator", strategy = "com.holovko.expertsystem.model.entity.generator.CustomStringIdGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private UserInfoEntity buyer;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private PropertyEntity property;
}
