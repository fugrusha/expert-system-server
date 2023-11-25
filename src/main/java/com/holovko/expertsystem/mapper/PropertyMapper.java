package com.holovko.expertsystem.mapper;

import com.holovko.expertsystem.model.dto.property.PropertyCreateDTO;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.model.dto.property.PropertyUpdateDTO;
import com.holovko.expertsystem.model.entity.ImageEntity;
import com.holovko.expertsystem.model.entity.PropertyEntity;
import com.holovko.expertsystem.model.entity.PropertyFeatureEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PropertyMapper {

    @Mapping(target = "sellerId", source = "entity.seller.id")
    @Mapping(target = "city", source = "entity.city.name")
    PropertyReadDTO toReadDTO(PropertyEntity entity);

    @Mapping(target = "sellerId", source = "entity.seller.id")
    @Mapping(target = "city", source = "entity.city.name")
    @Mapping(target = "images", source = "imageEntities")
    @Mapping(target = "features", source = "propertyFeatureEntities")
    PropertyReadDTO toReadDTO(PropertyEntity entity, List<PropertyFeatureEntity> propertyFeatureEntities, List<ImageEntity> imageEntities);

    @Mapping(target = "city", ignore = true)
    PropertyEntity toEntity(PropertyCreateDTO createDTO);

    @Mapping(target = "city", ignore = true)
    void updateEntity(@MappingTarget PropertyEntity entity, PropertyUpdateDTO updateDTO);
}
