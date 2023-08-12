package com.holovko.expertsystem.service;

import com.holovko.expertsystem.model.dto.AnswerFormDTO;
import com.holovko.expertsystem.resolver.DefaultResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final DefaultResolver resolver;

    public List<String> apply(AnswerFormDTO answerFormDTO) {
        log.info("Recieved: {}", answerFormDTO);

        Set<AnswerFormDTO.Answer> answers = answerFormDTO.getAnswers().stream()
                .filter(answer -> !answer.getQuestionKey().isBlank())
                .collect(Collectors.toSet());

        List<String> recommendations = resolver.solve(answers);

        log.info("Result: {}", recommendations);

        return recommendations;
    }
}
