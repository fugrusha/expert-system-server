package com.holovko.expertsystem.model.dto;

import lombok.Data;

@Data
public class RuleCreateDTO {

    private String name;

    private String description;

    private Integer priority;

    private String condition;

    private String action;
}
