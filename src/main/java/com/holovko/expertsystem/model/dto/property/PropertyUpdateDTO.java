package com.holovko.expertsystem.model.dto.property;

import com.holovko.expertsystem.model.entity.PropertyStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PropertyUpdateDTO {

    private String city;

    private BigDecimal price;

    private int bedrooms;

    private int bathrooms;

    private int squareFootage;

    private String title;

    private String description;

    private PropertyStatus status;

    private List<String> images;
}
