package com.holovko.expertsystem.repository.mongo;

import com.holovko.expertsystem.model.document.RuleDocument;
import com.holovko.expertsystem.model.document.RuleGroupDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RuleGroupDocumentRepository extends MongoRepository<RuleGroupDocument, String> {

    Optional<RuleDocument> findByGroupName(String name);
}
