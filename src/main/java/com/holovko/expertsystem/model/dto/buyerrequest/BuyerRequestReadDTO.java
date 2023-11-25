package com.holovko.expertsystem.model.dto.buyerrequest;

import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoReadDTO;
import com.holovko.expertsystem.model.entity.BuyerRequestStatus;
import lombok.Data;

import java.time.Instant;

@Data
public class BuyerRequestReadDTO {

    private String id;

    private PropertyReadDTO property;

    private UserInfoReadDTO buyer;

    private UserInfoReadDTO seller;

    private Instant timestamp;

    private BuyerRequestStatus status;
}
