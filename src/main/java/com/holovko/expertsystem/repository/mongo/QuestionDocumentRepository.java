package com.holovko.expertsystem.repository.mongo;

import com.holovko.expertsystem.model.document.QuestionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface QuestionDocumentRepository extends MongoRepository<QuestionDocument, String> {

    Optional<QuestionDocument> findByKey(String key);
}
