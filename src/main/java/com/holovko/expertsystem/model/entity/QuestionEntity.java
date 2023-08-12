package com.holovko.expertsystem.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String key;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private String question;

    @ElementCollection
    @CollectionTable(name = "answer_options")
    @Column(name = "answer_options")
    private List<String> answerOptions;

    private String comment;

    private Integer order;
}
