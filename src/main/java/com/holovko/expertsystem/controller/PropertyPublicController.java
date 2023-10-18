package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertyPublicController {

    private final PropertyService propertyService;

    @GetMapping
    public List<PropertyReadDTO> getProperties() {
        log.info("getProperties");
        return propertyService.getAllProperties();
    }

    @GetMapping("/{propertyId}")
    public PropertyReadDTO getProperty(
            @PathVariable String propertyId
    ) {
        log.info("getProperty: propertyId={}", propertyId);
        return propertyService.getPropertyById(propertyId);
    }
}
