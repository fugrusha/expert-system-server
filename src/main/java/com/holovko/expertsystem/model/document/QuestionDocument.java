package com.holovko.expertsystem.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("questions")
public class QuestionDocument {

    @Id
    private String id;

    private String key;

    private QuestionType questionType;

    private String question;

    private List<String> answerOptions;

    private String comment;

    private Integer order;
}
