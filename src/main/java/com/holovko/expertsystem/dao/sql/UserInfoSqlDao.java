package com.holovko.expertsystem.dao.sql;

import com.holovko.expertsystem.dao.UserInfoDao;
import com.holovko.expertsystem.exception.EntityAlreadyExistsException;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.UserInfoMapper;
import com.holovko.expertsystem.model.dto.seller.SellerCreateDTO;
import com.holovko.expertsystem.model.dto.seller.SellerReadDTO;
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
    public SellerReadDTO createSeller(SellerCreateDTO createDTO) {
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
    public Optional<SellerReadDTO> getSellerById(String sellerId) {
        return userInfoRepository.findById(sellerId)
                .map(userInfoMapper::toReadDto);
    }
}
