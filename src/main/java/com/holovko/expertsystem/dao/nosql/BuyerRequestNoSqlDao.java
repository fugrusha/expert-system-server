package com.holovko.expertsystem.dao.nosql;

import com.holovko.expertsystem.dao.BuyerRequestDao;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.BuyerRequestMapper;
import com.holovko.expertsystem.model.document.BuyerRequestDocument;
import com.holovko.expertsystem.model.document.PropertyDocument;
import com.holovko.expertsystem.model.document.UserInfoDocument;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestCreateDTO;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestReadDTO;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestUpdateDTO;
import com.holovko.expertsystem.model.entity.BuyerRequestStatus;
import com.holovko.expertsystem.repository.mongo.BuyerRequestDocumentRepository;
import com.holovko.expertsystem.repository.mongo.PropertyDocumentRepository;
import com.holovko.expertsystem.repository.mongo.UserInfoDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Profile("mongo")
@Component
@RequiredArgsConstructor
public class BuyerRequestNoSqlDao implements BuyerRequestDao {

    private final BuyerRequestDocumentRepository buyerRequestRepository;
    private final UserInfoDocumentRepository userInfoRepository;
    private final PropertyDocumentRepository propertyRepository;
    private final BuyerRequestMapper buyerRequestMapper;

    @Override
    public BuyerRequestReadDTO createRequest(BuyerRequestCreateDTO createDTO) {
        UserInfoDocument buyer = userInfoRepository.findById(createDTO.getBuyerId())
                .orElseThrow(EntityNotFoundException::new);

        PropertyDocument property = propertyRepository.findById(createDTO.getPropertyId())
                .orElseThrow(EntityNotFoundException::new);

        UserInfoDocument seller = userInfoRepository.findById(property.getSellerId())
                .orElseThrow(EntityNotFoundException::new);

        BuyerRequestDocument buyerRequest = new BuyerRequestDocument();
        buyerRequest.setStatus(BuyerRequestStatus.NEW);
        buyerRequest.setTimestamp(Instant.now());
        buyerRequest.setBuyerId(buyer.getId());
        buyerRequest.setSellerId(seller.getId());
        buyerRequest.setPropertyId(property.getId());

        BuyerRequestDocument saved = buyerRequestRepository.save(buyerRequest);
        return buyerRequestMapper.toReadDto(saved, property, buyer, seller);
    }

    @Override
    public List<BuyerRequestReadDTO> getAllBuyerRequests(String buyerId) {
        List<BuyerRequestDocument> requests = buyerRequestRepository.findByBuyerId(buyerId);

        return requests.stream()
                .map(this::mapToReadDto)
                .toList();
    }

    @Override
    public BuyerRequestReadDTO getRequest(String requestId) {
        return buyerRequestRepository.findById(requestId)
                .map(this::mapToReadDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<BuyerRequestReadDTO> getAllSellerRequests(String sellerId) {
        List<BuyerRequestDocument> requests = buyerRequestRepository.findBySellerId(sellerId);

        return requests.stream()
                .map(this::mapToReadDto)
                .toList();
    }

    @Override
    public BuyerRequestReadDTO updateRequest(String requestId, BuyerRequestUpdateDTO updateDTO) {
        return buyerRequestRepository.findById(requestId)
                .map(request -> {
                    buyerRequestMapper.updateDocument(request, updateDTO);
                    return request;
                })
                .map(buyerRequestRepository::save)
                .map(this::mapToReadDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    private BuyerRequestReadDTO mapToReadDto(BuyerRequestDocument request) {
        UserInfoDocument buyer = userInfoRepository.findById(request.getBuyerId())
                .orElseThrow(EntityNotFoundException::new);
        PropertyDocument property = propertyRepository.findById(request.getPropertyId())
                .orElseThrow(EntityNotFoundException::new);
        UserInfoDocument seller = userInfoRepository.findById(request.getSellerId())
                .orElseThrow(EntityNotFoundException::new);
        return buyerRequestMapper.toReadDto(request, property, buyer, seller);
    }
}
