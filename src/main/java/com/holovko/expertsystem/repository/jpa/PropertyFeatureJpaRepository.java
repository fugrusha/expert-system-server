package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.PropertyFeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyFeatureJpaRepository extends JpaRepository<PropertyFeatureEntity, String> {
    List<PropertyFeatureEntity> findAllByPropertyId(String propertyId);

    void deleteAllByPropertyId(String propertyId);
}
