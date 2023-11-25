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
@RequestMapping("/api/v1/properties/{propertyId}/features")
@RequiredArgsConstructor
public class PropertyFeatureController {

    private final SellerService sellerService;

    @GetMapping
    public List<PropertyFeatureReadDTO> getAllPropertyFeatures(
            @PathVariable String propertyId
    ) {
        log.info("getAllPropertyFeatures: propertyId={}", propertyId);
        return sellerService.getAllPropertyFeatures(propertyId);
    }

    @PostMapping
    public PropertyFeatureReadDTO createPropertyFeature(
            @PathVariable String propertyId,
            @RequestBody PropertyFeatureCreateDTO createDTO
    ) {
        log.info("createPropertyFeature: propertyId={}, createDTO={}", propertyId, createDTO);
        return sellerService.createPropertyFeature(propertyId, createDTO);
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
            @PathVariable String propertyId,
            @PathVariable String featureId
    ) {
        log.info("deletePropertyFeature: propertyId={}, featureId={}", propertyId, featureId);
        sellerService.deletePropertyFeature(propertyId, featureId);
    }

    @DeleteMapping
    public void deleteAllFeatures(
            @PathVariable String propertyId
    ) {
        log.info("deleteAllFeatures: propertyId={}", propertyId);
        sellerService.deleteAllPropertyFeatures(propertyId);
    }
}
