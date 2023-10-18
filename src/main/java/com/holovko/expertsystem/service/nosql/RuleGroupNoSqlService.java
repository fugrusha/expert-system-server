package com.holovko.expertsystem.service.nosql;

import com.holovko.expertsystem.exception.EntityAlreadyExistsException;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.RuleMapper;
import com.holovko.expertsystem.model.dto.rule.RuleGroupCreateDTO;
import com.holovko.expertsystem.model.dto.rule.RuleGroupReadDTO;
import com.holovko.expertsystem.model.dto.rule.RuleGroupUpdateDTO;
import com.holovko.expertsystem.model.document.RuleGroupDocument;
import com.holovko.expertsystem.repository.mongo.RuleGroupDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleGroupNoSqlService {

    private final RuleGroupDocumentRepository ruleGroupDocumentRepository;
    private final RuleMapper ruleMapper;

    public RuleGroupReadDTO getRuleGroup(String id) {
        return ruleGroupDocumentRepository.findById(id)
                .map(ruleMapper::toReadDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<RuleGroupReadDTO> getRuleGroups() {
        return ruleGroupDocumentRepository.findAll()
                .stream()
                .map(ruleMapper::toReadDto)
                .toList();
    }

    public RuleGroupReadDTO createRuleGroup(RuleGroupCreateDTO createDTO) {
        ruleGroupDocumentRepository.findByGroupName(createDTO.getGroupName())
                .ifPresent(rule -> {
                    throw new EntityAlreadyExistsException(rule.getName());
                });
        RuleGroupDocument ruleDocument = ruleMapper.toDocument(createDTO);
        RuleGroupDocument savedRule = ruleGroupDocumentRepository.save(ruleDocument);
        return ruleMapper.toReadDto(savedRule);
    }

    public RuleGroupReadDTO updateRuleGroups(String id, RuleGroupUpdateDTO updateDto) {
        ruleGroupDocumentRepository.findByGroupName(updateDto.getGroupName())
                .ifPresent(rule -> {
                    throw new EntityAlreadyExistsException(rule.getName());
                });
        return ruleGroupDocumentRepository.findById(id)
                .map(existingRule -> {
                    ruleMapper.update(existingRule, updateDto);
                    return existingRule;
                })
                .map(ruleGroupDocumentRepository::save)
                .map(ruleMapper::toReadDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void deleteRuleGroup(String id) {
        ruleGroupDocumentRepository.deleteById(id);
    }
}
