package com.holovko.expertsystem.dao;

import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestCreateDTO;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestReadDTO;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestUpdateDTO;

import java.util.List;

public interface BuyerRequestDao {

    BuyerRequestReadDTO createRequest(BuyerRequestCreateDTO createDTO);

    List<BuyerRequestReadDTO> getAllBuyerRequests(String buyerId);

    BuyerRequestReadDTO getRequest(String requestId);

    List<BuyerRequestReadDTO> getAllSellerRequests(String sellerId);

    BuyerRequestReadDTO updateRequest(String requestId, BuyerRequestUpdateDTO updateDTO);
}
