package com.holovko.expertsystem.mapper;

import com.holovko.expertsystem.model.document.UserInfoDocument;
import com.holovko.expertsystem.model.dto.seller.UserInfoCreateDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoInternalDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoReadDTO;
import com.holovko.expertsystem.model.dto.user.UserRegisterDTO;
import com.holovko.expertsystem.model.entity.UserInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserInfoMapper {

    UserInfoEntity toEntity(UserInfoCreateDTO createDTO);

    UserInfoDocument toDocument(UserInfoCreateDTO createDTO);

    UserInfoReadDTO toReadDto(UserInfoEntity userInfoEntity);
    UserInfoReadDTO toReadDto(UserInfoDocument userInfoDocument);

    UserInfoInternalDTO toInternalDto(UserInfoEntity userInfoEntity);
    UserInfoInternalDTO toInternalDto(UserInfoDocument userInfoDocument);

    @Mapping(target = "password", source = "encodedPassword")
    UserInfoCreateDTO toCreateDto(UserRegisterDTO registerDTO, String encodedPassword);
}
