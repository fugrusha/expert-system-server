package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyJpaRepository extends JpaRepository<PropertyEntity, String> {
    List<PropertyEntity> findAllBySellerId(String sellerId);

}
