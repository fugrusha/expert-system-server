package com.holovko.expertsystem.service;

import com.holovko.expertsystem.exception.InvalidRequestException;
import com.holovko.expertsystem.model.dto.property.PropertyCreateDTO;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.model.dto.property.PropertyUpdateDTO;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureCreateDTO;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureReadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final PropertyService propertyService;
    private final PropertyFeatureService propertyFeatureService;

    public PropertyReadDTO updateProperty(
            String sellerId,
            String propertyId,
            PropertyUpdateDTO updateDTO) {
        if (!validateRequest(updateDTO)) {
            throw new InvalidRequestException("request is not valid");
        }

        validatePropertyOwner(sellerId, propertyId);
        PropertyReadDTO updated = propertyService.updateProperty(propertyId, updateDTO);

        return updated;
    }

    private boolean validateRequest(PropertyUpdateDTO updateDTO) {
        // Implement request validation logic
        // Return true if the request is valid, false otherwise
        return true;
    }

    public List<PropertyReadDTO> getProperties(String sellerId) {
        return propertyService.getPropertiesBySellerId(sellerId);
    }

    public PropertyReadDTO getProperty(String sellerId, String propertyId) {
        PropertyReadDTO existingProperty = propertyService.getPropertyById(propertyId);

        validatePropertyOwner(sellerId, propertyId);

        return existingProperty;
    }

    public PropertyReadDTO createProperty(String sellerId, PropertyCreateDTO createDTO) {
        if (!validateRequest(createDTO)) {
            throw new InvalidRequestException("request is not valid");
        }
        return propertyService.createProperty(sellerId, createDTO);
    }

    private boolean validateRequest(PropertyCreateDTO createDTO) {
        // Implement request validation logic
        // Return true if the request is valid, false otherwise
        return true;
    }

    public void deleteProperty(String sellerId, String propertyId) {
        validatePropertyOwner(sellerId, propertyId);
        propertyService.deleteProperty(propertyId);
    }

    private void validatePropertyOwner(String sellerId, String propertyId) {
        PropertyReadDTO existingProperty = propertyService.getPropertyById(propertyId);

        if (!existingProperty.getSellerId().equals(sellerId)) {
            throw new InvalidRequestException("Property is owned by other seller");
        }
    }

    public List<PropertyFeatureReadDTO> getAllPropertyFeatures(String sellerId, String propertyId) {
        return propertyFeatureService.getAllPropertyFeatures(propertyId);
    }

    public PropertyFeatureReadDTO createPropertyFeature(String sellerId, String propertyId, PropertyFeatureCreateDTO createDTO) {
        return propertyFeatureService.addPropertyFeature(propertyId, createDTO);
    }

    public void deletePropertyFeature(String sellerId, String propertyId, String featureId) {
        propertyFeatureService.deletePropertyFeature(propertyId, featureId);
    }
}
