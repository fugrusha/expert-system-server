package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.BuyerRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRequestJpaRepository extends JpaRepository<BuyerRequestEntity, Long> {
}
