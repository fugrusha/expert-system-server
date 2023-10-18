package com.holovko.expertsystem.dao.sql;

import com.holovko.expertsystem.dao.PropertyFeatureDao;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.PropertyFeatureMapper;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureCreateDTO;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureReadDTO;
import com.holovko.expertsystem.model.entity.PropertyEntity;
import com.holovko.expertsystem.model.entity.PropertyFeatureEntity;
import com.holovko.expertsystem.repository.jpa.PropertyFeatureJpaRepository;
import com.holovko.expertsystem.repository.jpa.PropertyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PropertyFeatureSqlDao implements PropertyFeatureDao {

    private final PropertyFeatureJpaRepository propertyFeatureRepository;
    private final PropertyJpaRepository propertyJpaRepository;
    private final PropertyFeatureMapper propertyFeatureMapper;

    @Override
    public void deletePropertyFeature(String propertyId, String featureId) {
        propertyFeatureRepository.deleteById(featureId);
    }

    @Override
    public List<PropertyFeatureReadDTO> getAllPropertyFeatures(String propertyId) {
        return propertyFeatureRepository.findAllByPropertyId(propertyId).stream()
                .map(propertyFeatureMapper::toReadDTO)
                .toList();
    }

    @Override
    public PropertyFeatureReadDTO addPropertyFeature(String propertyId, PropertyFeatureCreateDTO createDTO) {
        PropertyEntity propertyEntity = propertyJpaRepository.findById(propertyId)
                .orElseThrow(EntityNotFoundException::new);
        PropertyFeatureEntity featureEntity = propertyFeatureMapper.toEntity(createDTO);
        featureEntity.setProperty(propertyEntity);
        PropertyFeatureEntity saved = propertyFeatureRepository.save(featureEntity);
        return propertyFeatureMapper.toReadDTO(saved);
    }
}
