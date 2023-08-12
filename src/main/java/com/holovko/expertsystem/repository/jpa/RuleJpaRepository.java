package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuleJpaRepository extends JpaRepository<RuleEntity, Long> {

    Optional<RuleEntity> findByName(String name);
}
