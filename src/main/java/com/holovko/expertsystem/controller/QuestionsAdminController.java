package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.QuestionCreateDTO;
import com.holovko.expertsystem.model.dto.QuestionReadDTO;
import com.holovko.expertsystem.model.dto.QuestionUpdateDTO;
import com.holovko.expertsystem.service.QuestionService;
import com.holovko.expertsystem.service.nosql.QuestionNoSqlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/knowledge-base/questions")
@RequiredArgsConstructor
public class QuestionsAdminController {

    @Qualifier("questionNoSqlService")
    private final QuestionService questionService;


    @PostMapping
    public QuestionReadDTO createQuestion(@RequestBody QuestionCreateDTO createDTO) {
        log.info("Received createQuestion: {}", createDTO);
        return questionService.createQuestion(createDTO);
    }

    @PatchMapping("/{id}")
    public QuestionReadDTO patchQuestion(@PathVariable String id,
                                         @RequestBody QuestionUpdateDTO updateDTO) {
        log.info("Received patchQuestion: {}", updateDTO);
        return questionService.updateQuestion(id, updateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable String id) {
        log.info("Received deleteQuestion: {}", id);
        questionService.deleteQuestion(id);
    }

    @GetMapping("/{id}")
    public QuestionReadDTO getQuestion(@PathVariable String id) {
        log.info("Received getQuestion: {}", id);
        return questionService.getQuestion(id);
    }

    @GetMapping
    public List<QuestionReadDTO> getQuestions() {
        log.info("Received getQuestions");
        return questionService.getQuestions();
    }
}
