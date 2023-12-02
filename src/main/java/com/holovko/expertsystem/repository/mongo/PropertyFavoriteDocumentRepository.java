package com.holovko.expertsystem.repository.mongo;

import com.holovko.expertsystem.model.document.PropertyFavoriteDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyFavoriteDocumentRepository extends MongoRepository<PropertyFavoriteDocument, String> {
    List<PropertyFavoriteDocument> findAllByBuyerId(String buyerId);

    Optional<PropertyFavoriteDocument> findByBuyerIdAndPropertyId(String buyerId, String propertyId);
}
