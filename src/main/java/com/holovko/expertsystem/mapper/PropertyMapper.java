package com.holovko.expertsystem.mapper;

import com.holovko.expertsystem.model.dto.property.PropertyCreateDTO;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.model.dto.property.PropertyUpdateDTO;
import com.holovko.expertsystem.model.entity.PropertyEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PropertyMapper {

    @Mapping(target = "sellerId", source = "entity.seller.id")
    @Mapping(target = "city", source = "entity.city.name")
    PropertyReadDTO toReadDTO(PropertyEntity entity);

    PropertyEntity toEntity(PropertyCreateDTO createDTO);

    void updateEntity(@MappingTarget PropertyEntity entity, PropertyUpdateDTO updateDTO);
}
