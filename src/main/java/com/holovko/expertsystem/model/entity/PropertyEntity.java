package com.holovko.expertsystem.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "property_entity")
public class PropertyEntity {

    @Id
    @GeneratedValue(generator = "custom-generator")
    @GenericGenerator(name = "custom-generator", strategy = "com.holovko.expertsystem.model.entity.generator.CustomStringIdGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserInfoEntity seller;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    private BigDecimal price;

    private int bedrooms;

    private int bathrooms;

    private int squareFootage;

    private String description;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status;
}
