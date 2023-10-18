package com.holovko.expertsystem.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Table(name = "property_image")
public class ImageEntity {

    @Id
    @GeneratedValue(generator = "custom-generator")
    @GenericGenerator(name = "custom-generator", strategy = "com.holovko.expertsystem.model.entity.generator.CustomStringIdGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private PropertyEntity property;

    private String imageURL;

    private String caption;
}
