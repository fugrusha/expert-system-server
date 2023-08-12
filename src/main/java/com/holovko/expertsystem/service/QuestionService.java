package com.holovko.expertsystem.service;

import com.holovko.expertsystem.model.dto.QuestionCreateDTO;
import com.holovko.expertsystem.model.dto.QuestionReadDTO;
import com.holovko.expertsystem.model.dto.QuestionUpdateDTO;

import java.util.List;

public interface QuestionService {

    QuestionReadDTO getQuestion(String id);

    List<QuestionReadDTO> getQuestions();

    QuestionReadDTO createQuestion(QuestionCreateDTO createDTO);

    QuestionReadDTO updateQuestion(String id, QuestionUpdateDTO updateDto);

    void deleteQuestion(String id);
}
