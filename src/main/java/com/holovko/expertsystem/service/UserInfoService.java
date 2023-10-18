package com.holovko.expertsystem.service;

import com.holovko.expertsystem.dao.UserInfoDao;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.model.dto.property.PropertyUpdateDTO;
import com.holovko.expertsystem.model.dto.seller.SellerCreateDTO;
import com.holovko.expertsystem.model.dto.seller.SellerReadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoDao userInfoDao;

    public Page<SellerReadDTO> getSellers(Pageable pageable) {
        return null;
    }


    public SellerReadDTO getSeller(String sellerId) {
        return userInfoDao.getSellerById(sellerId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public SellerReadDTO registerSeller(SellerCreateDTO createDTO) {
        return userInfoDao.createSeller(createDTO);
    }

    public SellerReadDTO updateSeller(String sellerId, PropertyUpdateDTO updateDTO) {
        return null;
    }


    public void deleteSeller(String sellerId) {

    }
}
