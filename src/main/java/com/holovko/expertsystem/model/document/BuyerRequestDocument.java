package com.holovko.expertsystem.model.document;

import com.holovko.expertsystem.model.entity.BuyerRequestStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document("buyer_requests")
public class BuyerRequestDocument {

    @Id
    private String id;

    private String propertyId;

    private String buyerId;

    private String sellerId;

    private Instant timestamp;

    private BuyerRequestStatus status;
}
