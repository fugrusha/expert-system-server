package com.holovko.expertsystem.mapper;

import com.holovko.expertsystem.model.document.CityDocument;
import com.holovko.expertsystem.model.dto.CityReadDTO;
import com.holovko.expertsystem.model.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CityMapper {

    CityReadDTO toReadDto(CityEntity cityEntity);

    CityReadDTO toReadDto(CityDocument cityDocument);
}
