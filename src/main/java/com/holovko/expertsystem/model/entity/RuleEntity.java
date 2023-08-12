package com.holovko.expertsystem.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "rules")
public class RuleEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Integer priority;

    private String condition;

    private String action;
}
