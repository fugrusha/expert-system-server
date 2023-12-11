package com.holovko.expertsystem.repository.mongo;

import com.holovko.expertsystem.model.document.PropertyDocument;
import com.holovko.expertsystem.model.entity.PropertyStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PropertyDocumentRepository extends MongoRepository<PropertyDocument, String> {

    List<PropertyDocument> findAllByStatus(PropertyStatus status);

    @Query("{$or : [{'title': { $regex: ?0, $options:'i' }}, " +
            "{'description': { $regex: ?0, $options:'i' }}," +
            " {'city': { $regex: ?0, $options:'i' }}]}")
    List<PropertyDocument> findWithSearch(String search);

    List<PropertyDocument> findAllBySellerId(String sellerId);
}
