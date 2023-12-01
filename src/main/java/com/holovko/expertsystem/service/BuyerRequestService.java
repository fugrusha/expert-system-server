package com.holovko.expertsystem.service;

import com.holovko.expertsystem.dao.BuyerRequestDao;
import com.holovko.expertsystem.dao.PropertyDao;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestCreateDTO;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestReadDTO;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestUpdateDTO;
import com.holovko.expertsystem.model.dto.property.PropertyUpdateDTO;
import com.holovko.expertsystem.model.entity.BuyerRequestStatus;
import com.holovko.expertsystem.model.entity.PropertyStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerRequestService {

    private final BuyerRequestDao buyerRequestDao;
    private final PropertyDao propertyDao;

    public BuyerRequestReadDTO createRequest(BuyerRequestCreateDTO createDTO) {
        return buyerRequestDao.createRequest(createDTO);
    }

    public List<BuyerRequestReadDTO> getAllBuyerRequests(String buyerId) {
        return buyerRequestDao.getAllBuyerRequests(buyerId);
    }

    public BuyerRequestReadDTO getBuyerRequest(String requestId) {
        return buyerRequestDao.getRequest(requestId);
    }

    public List<BuyerRequestReadDTO> getAllSellerRequests(String sellerId) {
        return buyerRequestDao.getAllSellerRequests(sellerId);
    }

    @Transactional
    public BuyerRequestReadDTO updateBuyerRequest(String requestId, BuyerRequestUpdateDTO updateDTO) {
        if (BuyerRequestStatus.COMPLETED.equals(updateDTO.getStatus())) {
            String propertyId = buyerRequestDao.getRequest(requestId).getProperty().getId();
            PropertyUpdateDTO propertyUpdateDTO = new PropertyUpdateDTO();
            propertyUpdateDTO.setStatus(PropertyStatus.SOLD);

            propertyDao.update(propertyId, propertyUpdateDTO);
        }
        return buyerRequestDao.updateRequest(requestId, updateDTO);
    }
}
