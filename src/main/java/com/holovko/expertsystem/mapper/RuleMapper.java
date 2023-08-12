package com.holovko.expertsystem.mapper;

import com.holovko.expertsystem.model.dto.*;
import com.holovko.expertsystem.model.document.RuleDocument;
import com.holovko.expertsystem.model.document.RuleGroupDocument;
import com.holovko.expertsystem.model.entity.RuleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RuleMapper {

    RuleReadDTO toReadDto(RuleDocument document);
    RuleDocument toDocument(RuleCreateDTO createDTO);
    void update(@MappingTarget RuleDocument ruleDocument, RuleUpdateDTO updateDTO);


    RuleGroupReadDTO toReadDto(RuleGroupDocument document);
    RuleGroupDocument toDocument(RuleGroupCreateDTO createDTO);
    void update(@MappingTarget RuleGroupDocument ruleDocument, RuleGroupUpdateDTO updateDTO);

    RuleReadDTO toReadDto(RuleEntity entity);
    RuleEntity toEntity(RuleCreateDTO createDTO);
    void update(@MappingTarget RuleEntity ruleEntity, RuleUpdateDTO updateDTO);
}
