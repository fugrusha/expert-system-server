package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.seller.UserInfoCreateDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoReadDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoUpdateDTO;
import com.holovko.expertsystem.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final UserInfoService userInfoService;

    @GetMapping
    public Page<UserInfoReadDTO> getSellers(
            Pageable pageable
    ) {
        log.info("getSellers: pageable={}", pageable);
        return userInfoService.getSellers(pageable);
    }

    @GetMapping("/{sellerId}")
    public UserInfoReadDTO getSeller(
            @PathVariable String sellerId
    ) {
        log.info("getSeller: seller={}", sellerId);
        return userInfoService.getSeller(sellerId);
    }

    @PostMapping
    public UserInfoReadDTO registerSeller(
            @RequestBody UserInfoCreateDTO createDTO
    ) {
        log.info("registerSeller: createDTO={}", createDTO);
        return userInfoService.registerSeller(createDTO);
    }

    @PatchMapping("/{sellerId}")
    public UserInfoReadDTO updateSeller(
            @PathVariable String sellerId,
            @RequestBody UserInfoUpdateDTO updateDTO
    ) {
        log.info("updateSeller: seller={}, updateDTO={}", sellerId, updateDTO);
        return userInfoService.updateSeller(sellerId, updateDTO);
    }

    @DeleteMapping("/{sellerId}")
    public void deleteSeller(
            @PathVariable String sellerId
    ) {
        log.info("deleteSeller: seller={}", sellerId);
        userInfoService.deleteSeller(sellerId);
    }
}
