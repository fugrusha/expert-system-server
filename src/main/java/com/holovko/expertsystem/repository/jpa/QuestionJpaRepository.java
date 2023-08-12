package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionJpaRepository extends JpaRepository<QuestionEntity, Long> {

    Optional<QuestionEntity> findByKey(String key);
}
