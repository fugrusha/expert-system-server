package com.holovko.expertsystem.service.nosql;

import com.holovko.expertsystem.exception.EntityAlreadyExistsException;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.QuestionMapper;
import com.holovko.expertsystem.model.dto.QuestionCreateDTO;
import com.holovko.expertsystem.model.dto.QuestionReadDTO;
import com.holovko.expertsystem.model.dto.QuestionUpdateDTO;
import com.holovko.expertsystem.model.document.QuestionDocument;
import com.holovko.expertsystem.repository.mongo.QuestionDocumentRepository;
import com.holovko.expertsystem.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionNoSqlService implements QuestionService {
    private final QuestionDocumentRepository questionDocumentRepository;
    private final QuestionMapper questionMapper;

    public QuestionReadDTO getQuestion(String id) {
        return questionDocumentRepository.findById(id)
                .map(questionMapper::toReadDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<QuestionReadDTO> getQuestions() {
        return questionDocumentRepository.findAll(Sort.by(Sort.Direction.ASC, "order"))
                .stream()
                .map(questionMapper::toReadDto)
                .toList();
    }

    public QuestionReadDTO createQuestion(QuestionCreateDTO createDTO) {
        questionDocumentRepository.findByKey(createDTO.getKey())
                .ifPresent(questionDocument -> {
                    throw new EntityAlreadyExistsException(questionDocument.getKey());
                });
        QuestionDocument questionDocument = questionMapper.toDocument(createDTO);
        QuestionDocument savedQuestion = questionDocumentRepository.save(questionDocument);
        return questionMapper.toReadDto(savedQuestion);
    }

    public QuestionReadDTO updateQuestion(String id, QuestionUpdateDTO updateDto) {
        return questionDocumentRepository.findById(id)
                .map(existingQuestion -> {
                    questionMapper.update(existingQuestion, updateDto);
                    return existingQuestion;
                })
                .map(questionDocumentRepository::save)
                .map(questionMapper::toReadDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void deleteQuestion(String id) {
        questionDocumentRepository.deleteById(id);
    }
}
