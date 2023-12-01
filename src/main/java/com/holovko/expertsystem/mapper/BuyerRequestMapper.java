package com.holovko.expertsystem.mapper;

import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestReadDTO;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestUpdateDTO;
import com.holovko.expertsystem.model.entity.BuyerRequestEntity;
import com.holovko.expertsystem.model.entity.CityEntity;
import com.holovko.expertsystem.model.entity.PropertyEntity;
import com.holovko.expertsystem.model.entity.UserInfoEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {PropertyMapper.class, UserInfoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface BuyerRequestMapper {

    @Mapping(target = "id", source = "buyerRequestEntity.requestId")
    @Mapping(target = "timestamp", source = "buyerRequestEntity.timestamp")
    @Mapping(target = "status", source = "buyerRequestEntity.status")
    @Mapping(target = "property", source = "propertyEntity")
    @Mapping(target = "buyer", source = "buyerEntity")
    @Mapping(target = "seller", source = "sellerEntity")
    BuyerRequestReadDTO toReadDto(
            BuyerRequestEntity buyerRequestEntity,
            PropertyEntity propertyEntity,
            UserInfoEntity buyerEntity,
            UserInfoEntity sellerEntity
    );

    default String mapCityName(CityEntity cityEntity) {
        return cityEntity.getName();
    }

    void updateEntity(@MappingTarget BuyerRequestEntity buyerRequest, BuyerRequestUpdateDTO updateDTO);
}
