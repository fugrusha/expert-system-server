package com.holovko.expertsystem.model.dto;

import com.holovko.expertsystem.model.entity.BuyerRequestStatus;
import lombok.Data;

import java.time.Instant;

@Data
public class BuyerRequestDTO {

    private String id;

    private Long propertyId;

    private Long buyerId;

    private Instant timestamp;

    private BuyerRequestStatus status;
}
