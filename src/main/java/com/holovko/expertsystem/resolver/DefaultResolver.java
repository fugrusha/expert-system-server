package com.holovko.expertsystem.resolver;

import com.holovko.expertsystem.model.dto.AnswerFormDTO;
import com.holovko.expertsystem.model.dto.rule.RuleGroupReadDTO;
import com.holovko.expertsystem.model.dto.rule.RuleReadDTO;
import com.holovko.expertsystem.model.document.RuleGroupType;
import com.holovko.expertsystem.service.nosql.RuleGroupNoSqlService;
import com.holovko.expertsystem.service.nosql.RuleNoSqlService;
import lombok.RequiredArgsConstructor;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.support.composite.CompositeRule;
import org.jeasy.rules.support.composite.ConditionalRuleGroup;
import org.jeasy.rules.support.composite.UnitRuleGroup;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DefaultResolver {

    private final RuleNoSqlService ruleService;
    private final RuleGroupNoSqlService ruleGroupService;

    public List<String> solve(Collection<AnswerFormDTO.Answer> answers) {
        Facts facts = new Facts();
        answers.forEach(answer -> facts.put(answer.getQuestionKey(), answer.getAnswer()));
        List<String> recommendations = new ArrayList<>();
        facts.put("recommendations", recommendations);

        Rules rules = collectAllRules();
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);

        return recommendations;
    }


    private Rules collectAllRules() {
        Set<Rule> singleRules = getSingleRules();
        Set<CompositeRule> compositeRules = getCompositeRules();

        HashSet<Rule> set = new HashSet<>();
        set.addAll(singleRules);
        set.addAll(compositeRules);

        return new Rules(set);
    }

    private Set<Rule> getSingleRules() {
        return ruleService.getRules()
                .stream()
                .map(this::toRule)
                .collect(Collectors.toSet());
    }

    private Rule toRule(RuleReadDTO ruleReadDTO) {
        return new MVELRule()
                .name(ruleReadDTO.getName())
                .description(ruleReadDTO.getDescription())
                .priority(ruleReadDTO.getPriority())
                .when(ruleReadDTO.getCondition())
                .then(addToRecommentations(ruleReadDTO.getAction()));
    }

    private String addToRecommentations(String advice) {
        return String.format("recommendations.add(\"%s\");", advice);
    }

    private Set<CompositeRule> getCompositeRules() {
        Set<UnitRuleGroup> unitRuleGroups = ruleGroupService.getRuleGroups().stream()
                .filter(group -> RuleGroupType.UNIT_GROUP.equals(group.getGroupType()))
                .map(group -> {
                    UnitRuleGroup unitRuleGroup = new UnitRuleGroup();
                    group.getRules().stream().map(this::toRule).forEach(unitRuleGroup::addRule);
                    return unitRuleGroup;
                }).collect(Collectors.toSet());

        Set<ConditionalRuleGroup> conditionalRuleGroups = ruleGroupService.getRuleGroups().stream()
                .filter(group -> RuleGroupType.CONDITIONAL_GROUP.equals(group.getGroupType()))
                .map(group -> {
                    ConditionalRuleGroup unitRuleGroup = new ConditionalRuleGroup();
                    group.getRules().stream().map(this::toRule).forEach(unitRuleGroup::addRule);
                    return unitRuleGroup;
                }).collect(Collectors.toSet());

        return Stream.concat(unitRuleGroups.stream(), conditionalRuleGroups.stream()).collect(Collectors.toSet());
    }

    private Rule toRule(RuleGroupReadDTO.RuleReadDTO ruleReadDTO) {
        return new MVELRule()
                .name(ruleReadDTO.getName())
                .description(ruleReadDTO.getDescription())
                .priority(ruleReadDTO.getPriority())
                .when(ruleReadDTO.getCondition())
                .then(ruleReadDTO.getAction());
    }
}
