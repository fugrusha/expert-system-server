package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityJpaRepository extends JpaRepository<CityEntity, String> {
    Optional<CityEntity> findByName(String name);
}
