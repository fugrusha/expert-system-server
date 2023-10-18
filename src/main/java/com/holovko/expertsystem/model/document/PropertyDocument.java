package com.holovko.expertsystem.model.document;

import com.holovko.expertsystem.model.entity.PropertyStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("properties")
public class PropertyDocument {

    @Id
    private String id;

    private String sellerID;

    private double price;

    private String address;

    private int bedrooms;

    private int bathrooms;

    private int squareFootage;

    private String description;

    private PropertyStatus status;

    private List<String> features;

    private List<Image> images;


    @Data
    public static class Image {

        private String imageURL;

        private String caption;
    }
}
