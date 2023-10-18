package com.holovko.expertsystem.repository.jpa;

import com.holovko.expertsystem.model.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoJpaRepository extends JpaRepository<UserInfoEntity, String> {
    Optional<UserInfoEntity> findByUsername(String username);
}
