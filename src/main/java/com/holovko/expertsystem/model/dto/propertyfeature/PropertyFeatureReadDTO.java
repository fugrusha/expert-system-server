package com.holovko.expertsystem.model.dto.propertyfeature;

import lombok.Data;

@Data
public class PropertyFeatureReadDTO {

    private String id;

    private String propertyId;

    private String featureName;

    private String description;
}
