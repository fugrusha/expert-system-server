package com.holovko.expertsystem.service;

import com.holovko.expertsystem.dao.UserInfoDao;
import com.holovko.expertsystem.exception.EntityAlreadyExistsException;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.UserInfoMapper;
import com.holovko.expertsystem.model.dto.seller.UserInfoCreateDTO;
import com.holovko.expertsystem.model.dto.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoDao userInfoDao;
    private final PasswordEncoder passwordEncoder;

    private final UserInfoMapper userInfoMapper;

    public UserInfoReadDTO login(UserLoginDTO loginDTO) {

        UserInfoInternalDTO userDTO = userInfoDao.findByUsername(loginDTO.getUsername())
                .orElseThrow(EntityNotFoundException::new);

        if (!passwordEncoder.matches(CharBuffer.wrap(loginDTO.getPassword()), userDTO.password())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return userInfoDao.findById(userDTO.id())
                .orElseThrow(EntityNotFoundException::new);
    }

    public UserInfoReadDTO getUser(String sellerId) {
        return userInfoDao.findById(sellerId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public UserInfoReadDTO updateUser(String userId, UserInfoUpdateDTO updateDTO) {
        return null;
    }

    public UserInfoReadDTO register(UserRegisterDTO registerDTO) {

        userInfoDao.findByUsername(registerDTO.getUsername())
                .ifPresent(user -> {
                    throw new EntityAlreadyExistsException("User already exists");
                });

        String encodedPassword = passwordEncoder.encode(CharBuffer.wrap(registerDTO.getPassword()));
        UserInfoCreateDTO createDTO = userInfoMapper.toCreateDto(registerDTO, encodedPassword);

        return userInfoDao.createUser(createDTO);
    }
}
