package com.holovko.expertsystem.dao.nosql;

import com.holovko.expertsystem.dao.UserInfoDao;
import com.holovko.expertsystem.exception.EntityAlreadyExistsException;
import com.holovko.expertsystem.mapper.UserInfoMapper;
import com.holovko.expertsystem.model.document.UserInfoDocument;
import com.holovko.expertsystem.model.dto.seller.UserInfoCreateDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoInternalDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoReadDTO;
import com.holovko.expertsystem.model.entity.UserType;
import com.holovko.expertsystem.repository.mongo.UserInfoDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Profile("mongo")
@Component
@RequiredArgsConstructor
public class UserInfoNoSqlDao implements UserInfoDao {

    private final UserInfoDocumentRepository userInfoRepository;
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

        UserInfoDocument userInfo = userInfoMapper.toDocument(createDTO);
        userInfo.setUserType(UserType.SELLER);
        UserInfoDocument saved = userInfoRepository.save(userInfo);
        return userInfoMapper.toReadDto(saved);
    }

    @Override
    public Optional<UserInfoReadDTO> findById(String userId) {
        return userInfoRepository.findById(userId)
                .map(userInfoMapper::toReadDto);
    }

    @Override
    public UserInfoReadDTO createUser(UserInfoCreateDTO createDTO) {
        UserInfoDocument userInfo = userInfoMapper.toDocument(createDTO);
        userInfo = userInfoRepository.save(userInfo);
        return userInfoMapper.toReadDto(userInfo);
    }
}
