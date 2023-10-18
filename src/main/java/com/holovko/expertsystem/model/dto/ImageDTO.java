package com.holovko.expertsystem.model.dto;

import lombok.Data;

@Data
public class ImageDTO {

    private String id;

    private String propertyId;

    private String imageURL;

    private String caption;
}
