package com.holovko.expertsystem.dao;

import com.holovko.expertsystem.model.dto.seller.SellerCreateDTO;
import com.holovko.expertsystem.model.dto.seller.SellerReadDTO;

import java.util.Optional;

public interface UserInfoDao {
    SellerReadDTO createSeller(SellerCreateDTO createDTO);

    Optional<SellerReadDTO> getSellerById(String sellerId);
}
