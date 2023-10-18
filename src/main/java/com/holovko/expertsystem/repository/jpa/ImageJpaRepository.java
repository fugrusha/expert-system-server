package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageJpaRepository extends JpaRepository<ImageEntity, Long> {
}
