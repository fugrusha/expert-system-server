package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.AnswerFormDTO;
import com.holovko.expertsystem.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class UserApplicationController {

    private final UserApplicationService service;

    @PostMapping
    public List<String> applyUserAnswers(@RequestBody AnswerFormDTO answerFormDTO) {
        log.info("Received applyUserAnswers: {}", answerFormDTO);
        return service.apply(answerFormDTO);
    }
}
