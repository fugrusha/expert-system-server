package com.holovko.expertsystem.service;

import com.holovko.expertsystem.model.dto.RuleCreateDTO;
import com.holovko.expertsystem.model.dto.RuleReadDTO;
import com.holovko.expertsystem.model.dto.RuleUpdateDTO;

import java.util.List;

public interface RuleService {

    RuleReadDTO getRule(String id);

    List<RuleReadDTO> getRules();

    RuleReadDTO createRule(RuleCreateDTO createDTO);

    RuleReadDTO updateRule(String id, RuleUpdateDTO updateDto);

    void deleteRule(String id);
}
