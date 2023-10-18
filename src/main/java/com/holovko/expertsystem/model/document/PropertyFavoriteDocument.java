package com.holovko.expertsystem.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("property_favorites")
public class PropertyFavoriteDocument {

    @Id
    private String id;

    private String buyerID;

    private String propertyID;
}
