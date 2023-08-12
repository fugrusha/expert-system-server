package com.holovko.expertsystem.model.dto;

import com.holovko.expertsystem.model.document.RuleGroupType;
import lombok.Data;

import java.util.List;

@Data
public class RuleGroupUpdateDTO {

    private RuleGroupType groupType;

    private String groupName;

    private String description;

    private List<RuleCreateDTO> rules;
}
