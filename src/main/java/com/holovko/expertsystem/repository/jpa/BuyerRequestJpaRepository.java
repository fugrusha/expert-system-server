package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.BuyerRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyerRequestJpaRepository extends JpaRepository<BuyerRequestEntity, String> {
    List<BuyerRequestEntity> findByBuyerId(String buyerId);

    List<BuyerRequestEntity> findByPropertySellerId(String sellerId);
}
