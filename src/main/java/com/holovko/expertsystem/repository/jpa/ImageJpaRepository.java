package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageJpaRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findByPropertyId(String propertyId);
}
