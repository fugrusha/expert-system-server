package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.property.PropertyCreateDTO;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.model.dto.property.PropertyUpdateDTO;
import com.holovko.expertsystem.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/sellers/{sellerId}/properties")
@RequiredArgsConstructor
public class SellerPropertyController {

    private final SellerService sellerService;

    @GetMapping
    public List<PropertyReadDTO> getProperties(@PathVariable String sellerId) {
        log.info("getSellerProperties: seller={}", sellerId);
        return sellerService.getProperties(sellerId);
    }

    @GetMapping("/{propertyId}")
    public PropertyReadDTO getProperty(
            @PathVariable String sellerId,
            @PathVariable String propertyId
    ) {
        log.info("getSellerProperty: seller={}, propertyId={}", sellerId, propertyId);
        return sellerService.getProperty(sellerId, propertyId);
    }

    @PostMapping
    public PropertyReadDTO createProperty(
            @PathVariable String sellerId,
            @RequestBody PropertyCreateDTO createDTO
    ) {
        log.info("createSellerProperty: seller={}, createDTO={}", sellerId, createDTO);
        return sellerService.createProperty(sellerId, createDTO);
    }

    @PatchMapping("/{propertyId}")
    public PropertyReadDTO updateProperty(
            @PathVariable String sellerId,
            @PathVariable String propertyId,
            @RequestBody PropertyUpdateDTO updateDTO
    ) {
        log.info("updateSellerProperty: seller={}, propertyId={}, updateDTO={}", sellerId, propertyId, updateDTO);
        return sellerService.updateProperty(sellerId, propertyId, updateDTO);
    }

    @DeleteMapping("/{propertyId}")
    public void deleteProperty(
            @PathVariable String sellerId,
            @PathVariable String propertyId
    ) {
        log.info("deleteSellerProperty: seller={}, propertyId={}", sellerId, propertyId);
        sellerService.deleteProperty(sellerId, propertyId);
    }
}
