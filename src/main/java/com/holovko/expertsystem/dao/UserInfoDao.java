package com.holovko.expertsystem.dao;

import com.holovko.expertsystem.model.dto.seller.UserInfoCreateDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoInternalDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoReadDTO;

import java.util.Optional;

public interface UserInfoDao {

    Optional<UserInfoInternalDTO> findByUsername(String username);

    UserInfoReadDTO createSeller(UserInfoCreateDTO createDTO);

    Optional<UserInfoReadDTO> findById(String userId);

    UserInfoReadDTO createUser(UserInfoCreateDTO createDTO);
}
