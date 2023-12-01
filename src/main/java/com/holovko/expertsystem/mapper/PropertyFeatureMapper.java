package com.holovko.expertsystem.mapper;

import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureCreateDTO;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureReadDTO;
import com.holovko.expertsystem.model.entity.PropertyEntity;
import com.holovko.expertsystem.model.entity.PropertyFeatureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PropertyFeatureMapper {

    @Mapping(target = "propertyId", source = "property.id")
    PropertyFeatureReadDTO toReadDTO(PropertyFeatureEntity featureEntity);

    PropertyFeatureEntity toEntity(PropertyFeatureCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "featureName", source = "featureName")
    @Mapping(target = "property", source = "propertyEntity")
    PropertyFeatureEntity toEntity(String featureName, PropertyEntity propertyEntity);
}
