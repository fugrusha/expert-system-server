package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureCreateDTO;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureReadDTO;
import com.holovko.expertsystem.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/sellers/{sellerId}/properties/{propertyId}/features")
@RequiredArgsConstructor
public class SellerPropertyFeatureController {

    private final SellerService sellerService;

    @GetMapping
    public List<PropertyFeatureReadDTO> getAllPropertyFeatures(
            @PathVariable String sellerId,
            @PathVariable String propertyId
    ) {
        log.info("getAllPropertyFeatures: seller={}, propertyId={}", sellerId, propertyId);
        return sellerService.getAllPropertyFeatures(sellerId, propertyId);
    }

    @PostMapping
    public PropertyFeatureReadDTO createPropertyFeature(
            @PathVariable String sellerId,
            @PathVariable String propertyId,
            @RequestBody PropertyFeatureCreateDTO createDTO
    ) {
        log.info("createPropertyFeature: seller={}, propertyId={}, createDTO={}", sellerId, propertyId, createDTO);
        return sellerService.createPropertyFeature(sellerId, propertyId, createDTO);
    }

//    @PatchMapping("/{propertyId}")
//    public PropertyReadDTO updateProperty(
//            @PathVariable String sellerId,
//            @PathVariable String propertyId,
//            @RequestBody PropertyUpdateDTO updateDTO
//    ) {
//        log.info("updateSellerProperty: seller={}, propertyId={}, updateDTO={}", sellerId, propertyId, updateDTO);
//        return sellerService.updateProperty(sellerId, propertyId, updateDTO);
//    }

    @DeleteMapping("/{featureId}")
    public void deletePropertyFeature(
            @PathVariable String sellerId,
            @PathVariable String propertyId,
            @PathVariable String featureId
    ) {
        log.info("deletePropertyFeature: seller={}, propertyId={}, featureId={}", sellerId, propertyId, featureId);
        sellerService.deletePropertyFeature(sellerId, propertyId, featureId);
    }
}
