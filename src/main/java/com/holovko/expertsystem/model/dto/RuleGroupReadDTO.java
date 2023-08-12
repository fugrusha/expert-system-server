package com.holovko.expertsystem.model.dto;

import com.holovko.expertsystem.model.document.RuleGroupType;
import lombok.Data;

import java.util.List;

@Data
public class RuleGroupReadDTO {

    private String id;

    private RuleGroupType groupType;

    private String groupName;

    private String description;

    private List<RuleReadDTO> rules;

    @Data
    public static class RuleReadDTO {

        private String name;

        private String description;

        private Integer priority;

        private String condition;

        private String action;
    }

}
