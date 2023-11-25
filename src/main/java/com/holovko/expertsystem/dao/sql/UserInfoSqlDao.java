package com.holovko.expertsystem.dao.sql;

import com.holovko.expertsystem.dao.UserInfoDao;
import com.holovko.expertsystem.exception.EntityAlreadyExistsException;
import com.holovko.expertsystem.mapper.UserInfoMapper;
import com.holovko.expertsystem.model.dto.seller.UserInfoCreateDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoInternalDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoReadDTO;
import com.holovko.expertsystem.model.entity.UserInfoEntity;
import com.holovko.expertsystem.model.entity.UserType;
import com.holovko.expertsystem.repository.jpa.UserInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Component
@RequiredArgsConstructor
public class UserInfoSqlDao implements UserInfoDao {

    private final UserInfoJpaRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;

    @Override
    public Optional<UserInfoInternalDTO> findByUsername(String username) {
        return userInfoRepository.findByUsername(username)
                .map(userInfoMapper::toInternalDto);
    }

    @Override
    public UserInfoReadDTO createSeller(UserInfoCreateDTO createDTO) {
        userInfoRepository.findByUsername(createDTO.getUsername())
                .ifPresent(entity -> {
                    throw new EntityAlreadyExistsException(
                            "User with username=%s already exists".formatted(createDTO.getUsername()));
                });

        UserInfoEntity userInfoEntity = userInfoMapper.toEntity(createDTO);
        userInfoEntity.setUserType(UserType.SELLER);
        UserInfoEntity saved = userInfoRepository.save(userInfoEntity);
        return userInfoMapper.toReadDto(saved);
    }

    @Override
    public Optional<UserInfoReadDTO> findById(String userId) {
        return userInfoRepository.findById(userId)
                .map(userInfoMapper::toReadDto);
    }

    @Override
    public UserInfoReadDTO createUser(UserInfoCreateDTO createDTO) {
        UserInfoEntity userInfoEntity = userInfoMapper.toEntity(createDTO);
        userInfoEntity = userInfoRepository.save(userInfoEntity);
        return userInfoMapper.toReadDto(userInfoEntity);
    }
}
