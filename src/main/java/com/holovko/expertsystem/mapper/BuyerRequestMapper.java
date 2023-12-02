package com.holovko.expertsystem.mapper;

import com.holovko.expertsystem.model.document.BuyerRequestDocument;
import com.holovko.expertsystem.model.document.PropertyDocument;
import com.holovko.expertsystem.model.document.UserInfoDocument;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestReadDTO;
import com.holovko.expertsystem.model.dto.buyerrequest.BuyerRequestUpdateDTO;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureReadDTO;
import com.holovko.expertsystem.model.entity.BuyerRequestEntity;
import com.holovko.expertsystem.model.entity.CityEntity;
import com.holovko.expertsystem.model.entity.PropertyEntity;
import com.holovko.expertsystem.model.entity.UserInfoEntity;
import org.mapstruct.*;

import java.util.List;

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

    @Mapping(target = "id", source = "buyerRequest.id")
    @Mapping(target = "timestamp", source = "buyerRequest.timestamp")
    @Mapping(target = "status", source = "buyerRequest.status")
    @Mapping(target = "property", source = "property")
    @Mapping(target = "buyer", source = "buyer")
    @Mapping(target = "seller", source = "seller")
    BuyerRequestReadDTO toReadDto(
            BuyerRequestDocument buyerRequest,
            PropertyDocument property,
            UserInfoDocument buyer,
            UserInfoDocument seller
    );

    default String mapCityName(CityEntity cityEntity) {
        return cityEntity.getName();
    }

    void updateEntity(@MappingTarget BuyerRequestEntity buyerRequest, BuyerRequestUpdateDTO updateDTO);

    void updateDocument(@MappingTarget BuyerRequestDocument buyerRequest, BuyerRequestUpdateDTO updateDTO);

    default List<PropertyFeatureReadDTO> mapFeatures(List<String> features) {
        return List.of();
    }
}
