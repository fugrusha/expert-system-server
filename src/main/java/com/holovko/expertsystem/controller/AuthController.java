package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.config.UserAuthProvider;
import com.holovko.expertsystem.model.dto.user.UserAuthDTO;
import com.holovko.expertsystem.model.dto.user.UserLoginDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoReadDTO;
import com.holovko.expertsystem.model.dto.user.UserRegisterDTO;
import com.holovko.expertsystem.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserInfoService userInfoService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public UserAuthDTO login(@RequestBody UserLoginDTO loginDTO) {
        log.info("login: {}", loginDTO);
        UserInfoReadDTO userInfoReadDTO = userInfoService.login(loginDTO);

        String token = userAuthProvider.createToken(userInfoReadDTO);

        UserAuthDTO authDTO = new UserAuthDTO();
        authDTO.setId(userInfoReadDTO.getId());
        authDTO.setUserType(userInfoReadDTO.getUserType());
        authDTO.setUsername(userInfoReadDTO.getUsername());
        authDTO.setToken(token);
        return authDTO;
    }

    @PostMapping("/register")
    public UserAuthDTO register(@RequestBody UserRegisterDTO registerDTO) {
        log.info("register: {}", registerDTO);
        UserInfoReadDTO userInfoReadDTO = userInfoService.register(registerDTO);

        String token = userAuthProvider.createToken(userInfoReadDTO);

        UserAuthDTO authDTO = new UserAuthDTO();
        authDTO.setId(userInfoReadDTO.getId());
        authDTO.setUserType(userInfoReadDTO.getUserType());
        authDTO.setUsername(userInfoReadDTO.getUsername());
        authDTO.setToken(token);
        return authDTO;
    }
}
