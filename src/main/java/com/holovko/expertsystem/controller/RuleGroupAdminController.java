package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.RuleGroupCreateDTO;
import com.holovko.expertsystem.model.dto.RuleGroupReadDTO;
import com.holovko.expertsystem.model.dto.RuleGroupUpdateDTO;
import com.holovko.expertsystem.service.nosql.RuleGroupNoSqlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/rule-groups")
@RequiredArgsConstructor
public class RuleGroupAdminController {

    private final RuleGroupNoSqlService ruleGroupService;

    @PostMapping
    public RuleGroupReadDTO createRule(@RequestBody RuleGroupCreateDTO createDTO) {
        return ruleGroupService.createRuleGroup(createDTO);
    }

    @PatchMapping("/{id}")
    public RuleGroupReadDTO patchRule(@PathVariable String id, @RequestBody RuleGroupUpdateDTO updateDTO) {
        return ruleGroupService.updateRuleGroups(id, updateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRule(@PathVariable String id) {
        ruleGroupService.deleteRuleGroup(id);
    }

    @GetMapping("/{id}")
    public RuleGroupReadDTO getRule(@PathVariable String id) {
        return ruleGroupService.getRuleGroup(id);
    }

    @GetMapping
    public List<RuleGroupReadDTO> getRuleGroups() {
        return ruleGroupService.getRuleGroups();
    }
}
