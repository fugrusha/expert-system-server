package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.PropertyEntity;
import com.holovko.expertsystem.model.entity.PropertyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyJpaRepository extends JpaRepository<PropertyEntity, String> {
    List<PropertyEntity> findAllBySellerId(String sellerId);

    @Query("SELECT p FROM PropertyEntity p " +
            "WHERE lower(p.title) LIKE %:search% " +
            "OR lower(p.city.name) LIKE %:search% " +
            "OR lower(p.description) LIKE %:search% " +
            "AND p.status IN ('FOR_SALE')")
    List<PropertyEntity> findWithSearch(String search);

    List<PropertyEntity> findAllByStatus(PropertyStatus status);
}
