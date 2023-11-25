package com.holovko.expertsystem.service;

import com.holovko.expertsystem.dao.PropertyFeatureDao;
import com.holovko.expertsystem.dao.sql.PropertySqlDao;
import com.holovko.expertsystem.exception.InvalidRequestException;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureCreateDTO;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureReadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyFeatureService {

    private final PropertyFeatureDao propertyFeatureDao;

    public List<PropertyFeatureReadDTO> getAllPropertyFeatures(String propertyId) {
        return propertyFeatureDao.getAllPropertyFeatures(propertyId);
    }

    public PropertyFeatureReadDTO addPropertyFeature(String propertyId, PropertyFeatureCreateDTO createDTO) {
        return propertyFeatureDao.addPropertyFeature(propertyId, createDTO);
    }

    public void deletePropertyFeature(String propertyId, String featureId) {
        boolean belongsToProperty = propertyFeatureDao.getAllPropertyFeatures(propertyId).stream()
                .map(PropertyFeatureReadDTO::getId)
                .anyMatch(id -> id.equals(featureId));
        if (!belongsToProperty) {
            throw new InvalidRequestException("Feature does not belong to property");
        }
        propertyFeatureDao.deletePropertyFeature(propertyId, featureId);
    }

    public void deleteAll(String propertyId) {
        propertyFeatureDao.deleteAllByPropertyId(propertyId);
    }
}
