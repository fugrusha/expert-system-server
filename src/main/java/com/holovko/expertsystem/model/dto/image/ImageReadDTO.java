package com.holovko.expertsystem.model.dto.image;

import lombok.Data;

@Data
public class ImageReadDTO {

    private String id;

    private String imageURL;

    private String caption;
}
