package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestCreateDTO;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestReadDTO;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestUpdateDTO;
import com.holovko.expertsystem.service.BuyerRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/requests")
public class BuyerRequestController {

    private final BuyerRequestService buyerRequestService;

    @GetMapping(params = "buyerId")
    public List<BuyerRequestReadDTO> getAllBuyerRequests(@RequestParam String buyerId) {
        log.info("getAllBuyerRequests for buyerId = {}", buyerId);
        return buyerRequestService.getAllBuyerRequests(buyerId);
    }

    @GetMapping(params = "sellerId")
    public List<BuyerRequestReadDTO> getAllSellerRequests(@RequestParam String sellerId) {
        log.info("getAllSellerRequests for sellerId = {}", sellerId);
        return buyerRequestService.getAllSellerRequests(sellerId);
    }

    @GetMapping("/{requestId}")
    public BuyerRequestReadDTO getBuyerRequest(@PathVariable String requestId) {
        log.info("getBuyerRequest with requestId={}", requestId);
        return buyerRequestService.getBuyerRequest(requestId);
    }

    @PostMapping
    public BuyerRequestReadDTO createBuyerRequest(@RequestBody BuyerRequestCreateDTO createDTO) {
        log.info("createBuyerRequest createDto = {}", createDTO);
        return buyerRequestService.createRequest(createDTO);
    }

    @PatchMapping("/{requestId}")
    public BuyerRequestReadDTO updateBuyerRequest(
            @PathVariable String requestId,
            @RequestBody BuyerRequestUpdateDTO updateDTO
    ) {
        log.info("updateBuyerRequest requestId={}, updateDTO = {}", requestId, updateDTO);
        return buyerRequestService.updateBuyerRequest(requestId, updateDTO);
    }

}
