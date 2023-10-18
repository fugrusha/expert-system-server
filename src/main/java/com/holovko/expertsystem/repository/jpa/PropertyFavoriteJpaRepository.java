package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.PropertyFavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyFavoriteJpaRepository extends JpaRepository<PropertyFavoriteEntity, Long> {
}
