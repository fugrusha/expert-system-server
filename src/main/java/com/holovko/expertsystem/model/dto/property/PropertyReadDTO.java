package com.holovko.expertsystem.model.dto.property;

import com.holovko.expertsystem.model.dto.image.ImageReadDTO;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureReadDTO;
import com.holovko.expertsystem.model.entity.PropertyStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PropertyReadDTO {

    private String id;

    private String sellerId;

    private String city;

    private BigDecimal price;

    private int bedrooms;

    private int bathrooms;

    private int squareFootage;

    private String title;

    private String description;

    private PropertyStatus status;

    private List<ImageReadDTO> images;

    private List<PropertyFeatureReadDTO> features;
}
