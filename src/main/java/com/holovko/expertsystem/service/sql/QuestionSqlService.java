package com.holovko.expertsystem.service.sql;

import com.holovko.expertsystem.exception.EntityAlreadyExistsException;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.QuestionMapper;
import com.holovko.expertsystem.model.dto.question.QuestionCreateDTO;
import com.holovko.expertsystem.model.dto.question.QuestionReadDTO;
import com.holovko.expertsystem.model.dto.question.QuestionUpdateDTO;
import com.holovko.expertsystem.model.entity.QuestionEntity;
import com.holovko.expertsystem.repository.jpa.QuestionJpaRepository;
import com.holovko.expertsystem.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionSqlService implements QuestionService {

    private final QuestionJpaRepository questionJpaRepository;
    private final QuestionMapper questionMapper;

    @Override
    public QuestionReadDTO getQuestion(String id) {
        return questionJpaRepository.findById(Long.valueOf(id))
                .map(questionMapper::toReadDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<QuestionReadDTO> getQuestions() {
        return questionJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "order"))
                .stream()
                .map(questionMapper::toReadDto)
                .toList();
    }

    @Override
    public QuestionReadDTO createQuestion(QuestionCreateDTO createDTO) {
        questionJpaRepository.findByKey(createDTO.getKey())
                .ifPresent(question -> {
                    throw new EntityAlreadyExistsException(question.getKey());
                });
        QuestionEntity entity = questionMapper.toEntity(createDTO);
        QuestionEntity savedQuestion = questionJpaRepository.save(entity);
        return questionMapper.toReadDto(savedQuestion);
    }

    @Override
    public QuestionReadDTO updateQuestion(String id, QuestionUpdateDTO updateDto) {
        return questionJpaRepository.findById(Long.valueOf(id))
                .map(existingQuestion -> {
                    questionMapper.update(existingQuestion, updateDto);
                    return existingQuestion;
                })
                .map(questionJpaRepository::save)
                .map(questionMapper::toReadDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteQuestion(String id) {
        questionJpaRepository.deleteById(Long.valueOf(id));
    }
}
