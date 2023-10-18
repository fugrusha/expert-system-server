package com.holovko.expertsystem.model.dto.property;

import com.holovko.expertsystem.model.entity.PropertyStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PropertyUpdateDTO {

    private String cityId;

    private BigDecimal price;

    private int bedrooms;

    private int bathrooms;

    private int squareFootage;

    private String description;

    private PropertyStatus status;
}
