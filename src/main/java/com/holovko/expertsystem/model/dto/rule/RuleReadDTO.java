package com.holovko.expertsystem.model.dto.rule;

import lombok.Data;

@Data
public class RuleReadDTO {

    private String id;

    private String name;

    private String description;

    private Integer priority;

    private String condition;

    private String action;
}
