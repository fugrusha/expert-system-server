package com.holovko.expertsystem.dao.nosql;

import com.holovko.expertsystem.dao.PropertyFeatureDao;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureCreateDTO;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureReadDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("mongo")
@Component
public class PropertyFeatureNoSqlDao implements PropertyFeatureDao {
    @Override
    public void deletePropertyFeature(String propertyId, String featureId) {

    }

    @Override
    public List<PropertyFeatureReadDTO> getAllPropertyFeatures(String propertyId) {
        return null;
    }

    @Override
    public PropertyFeatureReadDTO addPropertyFeature(String propertyId, PropertyFeatureCreateDTO createDTO) {
        return null;
    }

    @Override
    public void deleteAllByPropertyId(String propertyId) {

    }
}
