package com.holovko.expertsystem.model.dto.buyerrequest;

import com.holovko.expertsystem.model.entity.BuyerRequestStatus;
import lombok.Data;

@Data
public class BuyerRequestUpdateDTO {
    private BuyerRequestStatus status;
}
