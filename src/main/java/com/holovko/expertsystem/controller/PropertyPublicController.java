package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertyPublicController {

    private final PropertyService propertyService;

    @GetMapping
    public List<PropertyReadDTO> getProperties(
            @RequestParam(required = false) String search
    ) {
        log.info("getProperties with search={}", search);
        return propertyService.getAllProperties(search);
    }

    @GetMapping("/{propertyId}")
    public PropertyReadDTO getProperty(
            @PathVariable String propertyId
    ) {
        log.info("getProperty: propertyId={}", propertyId);
        return propertyService.getPropertyById(propertyId);
    }
}
