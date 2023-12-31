package com.holovko.expertsystem.model.dto.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.holovko.expertsystem.model.document.QuestionType;
import lombok.Data;

import java.util.List;

@Data
public class QuestionReadDTO {

    private String id;

    private String key;

    private QuestionType questionType;

    private String question;

    @JsonProperty("answers")
    private List<String> answerOptions;

    private String comment;

    private Integer order;
}
