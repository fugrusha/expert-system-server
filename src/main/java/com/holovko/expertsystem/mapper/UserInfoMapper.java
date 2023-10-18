package com.holovko.expertsystem.mapper;

import com.holovko.expertsystem.model.dto.seller.SellerCreateDTO;
import com.holovko.expertsystem.model.dto.seller.SellerReadDTO;
import com.holovko.expertsystem.model.entity.UserInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserInfoMapper {

    UserInfoEntity toEntity(SellerCreateDTO createDTO);

    SellerReadDTO toReadDto(UserInfoEntity userInfoEntity);
}
