package com.holovko.expertsystem.repository.mongo;

import com.holovko.expertsystem.model.document.BuyerRequestDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BuyerRequestDocumentRepository extends MongoRepository<BuyerRequestDocument, String> {
    List<BuyerRequestDocument> findByBuyerId(String buyerId);

    List<BuyerRequestDocument> findBySellerId(String sellerId);
}
