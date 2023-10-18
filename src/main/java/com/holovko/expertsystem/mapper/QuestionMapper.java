package com.holovko.expertsystem.mapper;

import com.holovko.expertsystem.model.dto.question.QuestionCreateDTO;
import com.holovko.expertsystem.model.dto.question.QuestionReadDTO;
import com.holovko.expertsystem.model.dto.question.QuestionUpdateDTO;
import com.holovko.expertsystem.model.document.QuestionDocument;
import com.holovko.expertsystem.model.entity.QuestionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuestionMapper {

    QuestionReadDTO toReadDto(QuestionDocument document);

    QuestionDocument toDocument(QuestionCreateDTO createDTO);

    void update(@MappingTarget QuestionDocument document, QuestionUpdateDTO updateDTO);

    QuestionReadDTO toReadDto(QuestionEntity entity);

    QuestionEntity toEntity(QuestionCreateDTO createDTO);

    void update(@MappingTarget QuestionEntity entity, QuestionUpdateDTO updateDTO);
}
