package com.holovko.expertsystem.repository.mongo;

import com.holovko.expertsystem.model.document.CityDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CityDocumentRepository extends MongoRepository<CityDocument, String> {

    Optional<CityDocument> findByName(String name);
}
