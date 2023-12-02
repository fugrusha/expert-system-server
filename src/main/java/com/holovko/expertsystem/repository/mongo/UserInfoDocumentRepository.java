package com.holovko.expertsystem.repository.mongo;

import com.holovko.expertsystem.model.document.UserInfoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserInfoDocumentRepository extends MongoRepository<UserInfoDocument, String> {

    Optional<UserInfoDocument> findByUsername(String username);
}
