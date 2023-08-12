package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.RuleCreateDTO;
import com.holovko.expertsystem.model.dto.RuleReadDTO;
import com.holovko.expertsystem.model.dto.RuleUpdateDTO;
import com.holovko.expertsystem.service.RuleService;
import com.holovko.expertsystem.service.nosql.RuleNoSqlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/rules")
@RequiredArgsConstructor
public class RuleAdminController {

    @Qualifier("ruleNoSqlService")
    private final RuleService ruleService;

    @PostMapping
    public RuleReadDTO createRule(@RequestBody RuleCreateDTO createDTO) {
        log.info("Received createRule: {}", createDTO);
        return ruleService.createRule(createDTO);
    }

    @PatchMapping("/{id}")
    public RuleReadDTO patchRule(@PathVariable String id, @RequestBody RuleUpdateDTO updateDTO) {
        log.info("Received patchRule: {}", updateDTO);
        return ruleService.updateRule(id, updateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRule(@PathVariable String id) {
        log.info("Received deleteRule: {}", id);
        ruleService.deleteRule(id);
    }

    @GetMapping("/{id}")
    public RuleReadDTO getRule(@PathVariable String id) {
        log.info("Received getRule: {}", id);
        return ruleService.getRule(id);
    }

    @GetMapping
    public List<RuleReadDTO> getRules() {
        log.info("Received: getRules");
        return ruleService.getRules();
    }
}
