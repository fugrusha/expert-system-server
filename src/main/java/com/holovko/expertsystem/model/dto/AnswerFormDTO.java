package com.holovko.expertsystem.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnswerFormDTO {

    private List<Answer> answers;

    @Data
    public static class Answer {

        private String questionKey;

        private String answer;
    }
}
