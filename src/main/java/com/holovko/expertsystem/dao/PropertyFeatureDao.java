package com.holovko.expertsystem.dao;

import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureCreateDTO;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureReadDTO;

import java.util.List;

public interface PropertyFeatureDao {

    void deletePropertyFeature(String propertyId, String featureId);

    List<PropertyFeatureReadDTO> getAllPropertyFeatures(String propertyId);

    PropertyFeatureReadDTO addPropertyFeature(String propertyId, PropertyFeatureCreateDTO createDTO);
}
