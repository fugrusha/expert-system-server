package com.holovko.expertsystem.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("rules")
public class RuleDocument {

    @Id
    private String id;

    private String name;

    private String description;

    private Integer priority;

    private String condition;

    private String action;
}
