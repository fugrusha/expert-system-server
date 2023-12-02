package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.user.UserInfoReadDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoUpdateDTO;
import com.holovko.expertsystem.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/{userId}")
    public UserInfoReadDTO getUser(
            @PathVariable String userId
    ) {
        log.info("getUser: seller={}", userId);
        return userInfoService.getUser(userId);
    }

    @PatchMapping("/{sellerId}")
    public UserInfoReadDTO updateUser(
            @PathVariable String userId,
            @RequestBody UserInfoUpdateDTO updateDTO
    ) {
        log.info("updateSeller: userId={}, updateDTO={}", userId, updateDTO);
        return userInfoService.updateUser(userId, updateDTO);
    }
}
