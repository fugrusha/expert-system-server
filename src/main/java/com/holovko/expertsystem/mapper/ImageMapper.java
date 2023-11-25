package com.holovko.expertsystem.mapper;

import com.holovko.expertsystem.model.entity.ImageEntity;
import com.holovko.expertsystem.model.entity.PropertyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ImageMapper {

    @Mapping(target = "imageURL", source = "imageUrl")
    @Mapping(target = "property", source = "propertyEntity")
    ImageEntity toEntity(String imageUrl, PropertyEntity propertyEntity);
}
