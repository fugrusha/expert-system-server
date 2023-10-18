package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityJpaRepository extends JpaRepository<CityEntity, String> {
}
