package com.holovko.expertsystem.service;

import com.holovko.expertsystem.dao.PropertyDao;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.model.dto.property.PropertyCreateDTO;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.model.dto.property.PropertyUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyDao propertyDao;

    public List<PropertyReadDTO> getAllProperties(String search) {
       return propertyDao.findAll(search);
    }

    public PropertyReadDTO getPropertyById(String propertyId) {
        return propertyDao.findById(propertyId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public PropertyReadDTO updateProperty(String propertyId, PropertyUpdateDTO updateDTO) {
        return propertyDao.update(propertyId, updateDTO);
    }

    public List<PropertyReadDTO> getPropertiesBySellerId(String sellerId) {
        return propertyDao.getPropertiesBySellerId(sellerId);
    }

    public PropertyReadDTO createProperty(String sellerId, PropertyCreateDTO createDTO) {
        return propertyDao.create(sellerId, createDTO);
    }

    public void deleteProperty(String propertyId) {
        propertyDao.deleteById(propertyId);
    }
}
