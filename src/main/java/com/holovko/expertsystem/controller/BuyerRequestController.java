package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestCreateDTO;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestReadDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/buyers")
public class BuyerRequestController {

    @GetMapping("/{buyerId}")
    public List<BuyerRequestReadDTO> getAllBuyerRequests(String buyerId) {
        log.info("getAllBuyerRequests for buyerId = {}", buyerId);
        return null;
    }

    @PostMapping
    public BuyerRequestReadDTO createBuyerRequest(BuyerRequestCreateDTO createDTO) {
        log.info("createBuyerRequest createDto = {}", createDTO);
        return null;
    }

}
