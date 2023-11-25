package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.PropertyFavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyFavoriteJpaRepository extends JpaRepository<PropertyFavoriteEntity, String> {
    List<PropertyFavoriteEntity> findAllByBuyerId(String userId);

    Optional<PropertyFavoriteEntity> findByBuyerIdAndPropertyId(String userId, String propertyId);
}
