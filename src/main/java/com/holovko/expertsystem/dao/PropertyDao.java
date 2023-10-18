package com.holovko.expertsystem.dao;

import com.holovko.expertsystem.model.dto.property.PropertyCreateDTO;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.model.dto.property.PropertyUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface PropertyDao {

    Optional<PropertyReadDTO> findById(String id);

    List<PropertyReadDTO> findAll();

    PropertyReadDTO create(String sellerId, PropertyCreateDTO createDTO);

    PropertyReadDTO update(String id, PropertyUpdateDTO updateDTO);

    void deleteById(String id);

    List<PropertyReadDTO> getPropertiesBySellerId(String sellerId);
}
