package com.holovko.expertsystem.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "rulegroups")
public class RuleGroupDocument {

    @Id
    private String id;

    private RuleGroupType groupType;

    private String groupName;

    private String description;

    private List<Rule> rules;

    @Data
    public static class Rule {

        private String name;

        private String description;

        private Integer priority;

        private String condition;

        private String action;
    }
}
