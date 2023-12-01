package com.holovko.expertsystem.dao.sql;

import com.holovko.expertsystem.dao.BuyerRequestDao;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.BuyerRequestMapper;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestCreateDTO;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestReadDTO;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestUpdateDTO;
import com.holovko.expertsystem.model.entity.BuyerRequestEntity;
import com.holovko.expertsystem.model.entity.BuyerRequestStatus;
import com.holovko.expertsystem.model.entity.PropertyEntity;
import com.holovko.expertsystem.model.entity.UserInfoEntity;
import com.holovko.expertsystem.repository.jpa.BuyerRequestJpaRepository;
import com.holovko.expertsystem.repository.jpa.PropertyJpaRepository;
import com.holovko.expertsystem.repository.jpa.UserInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Transactional
@Component
@RequiredArgsConstructor
public class BuyerRequestSqlDao implements BuyerRequestDao {

    private final BuyerRequestJpaRepository buyerRequestRepository;
    private final UserInfoJpaRepository userInfoRepository;
    private final PropertyJpaRepository propertyRepository;
    private final BuyerRequestMapper buyerRequestMapper;

    @Override
    public BuyerRequestReadDTO createRequest(BuyerRequestCreateDTO createDTO) {

        UserInfoEntity buyer = userInfoRepository.findById(createDTO.getBuyerId())
                .orElseThrow(EntityNotFoundException::new);

        PropertyEntity propertyEntity = propertyRepository.findById(createDTO.getPropertyId())
                .orElseThrow(EntityNotFoundException::new);

        BuyerRequestEntity entity = new BuyerRequestEntity();
        entity.setStatus(BuyerRequestStatus.NEW);
        entity.setTimestamp(Instant.now());
        entity.setBuyer(buyer);
        entity.setProperty(propertyEntity);

        BuyerRequestEntity saved = buyerRequestRepository.save(entity);
        return buyerRequestMapper.toReadDto(saved, propertyEntity, buyer, propertyEntity.getSeller());
    }

    @Override
    public List<BuyerRequestReadDTO> getAllBuyerRequests(String buyerId) {

        List<BuyerRequestEntity> requests = buyerRequestRepository.findByBuyerId(buyerId);

        return requests.stream()
                .map(request -> {
                    UserInfoEntity buyer = request.getBuyer();
                    PropertyEntity propertyEntity = request.getProperty();
                    return buyerRequestMapper.toReadDto(request, propertyEntity, buyer, propertyEntity.getSeller());
                })
                .toList();
    }

    @Override
    public BuyerRequestReadDTO getRequest(String requestId) {
        return buyerRequestRepository.findById(requestId)
                .map(request -> {
                    UserInfoEntity buyer = request.getBuyer();
                    PropertyEntity propertyEntity = request.getProperty();
                    return buyerRequestMapper.toReadDto(request, propertyEntity, buyer, propertyEntity.getSeller());
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<BuyerRequestReadDTO> getAllSellerRequests(String sellerId) {
        List<BuyerRequestEntity> requests = buyerRequestRepository.findByPropertySellerId(sellerId);

        return requests.stream()
                .map(request -> {
                    UserInfoEntity buyer = request.getBuyer();
                    PropertyEntity propertyEntity = request.getProperty();
                    return buyerRequestMapper.toReadDto(request, propertyEntity, buyer, propertyEntity.getSeller());
                })
                .toList();
    }

    @Override
    public BuyerRequestReadDTO updateRequest(String requestId, BuyerRequestUpdateDTO updateDTO) {
        return buyerRequestRepository.findById(requestId)
                .map(request -> {
                    buyerRequestMapper.updateEntity(request, updateDTO);
                    return request;
                })
                .map(buyerRequestRepository::save)
                .map(request -> {
                    UserInfoEntity buyer = request.getBuyer();
                    PropertyEntity propertyEntity = request.getProperty();
                    return buyerRequestMapper.toReadDto(request, propertyEntity, buyer, propertyEntity.getSeller());
                })
                .orElseThrow(EntityNotFoundException::new);
    }
}
