package com.holovko.expertsystem.repository.mongo;

import com.holovko.expertsystem.model.document.RuleDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RuleDocumentRepository extends MongoRepository<RuleDocument, String> {
    Optional<RuleDocument> findByName(String name);
}
