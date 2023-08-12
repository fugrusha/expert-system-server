package com.holovko.expertsystem.service.nosql;

import com.holovko.expertsystem.exception.EntityAlreadyExistsException;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.RuleMapper;
import com.holovko.expertsystem.model.dto.RuleCreateDTO;
import com.holovko.expertsystem.model.dto.RuleReadDTO;
import com.holovko.expertsystem.model.dto.RuleUpdateDTO;
import com.holovko.expertsystem.model.document.RuleDocument;
import com.holovko.expertsystem.repository.mongo.RuleDocumentRepository;
import com.holovko.expertsystem.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleNoSqlService implements RuleService {

    private final RuleDocumentRepository ruleDocumentRepository;
    private final RuleMapper ruleMapper;

    @Override
    public RuleReadDTO getRule(String id) {
        return ruleDocumentRepository.findById(id)
                .map(ruleMapper::toReadDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<RuleReadDTO> getRules() {
        return ruleDocumentRepository.findAll()
                .stream()
                .map(ruleMapper::toReadDto)
                .toList();
    }

    @Override
    public RuleReadDTO createRule(RuleCreateDTO createDTO) {
        ruleDocumentRepository.findByName(createDTO.getName())
                .ifPresent(rule -> {
                    throw new EntityAlreadyExistsException(rule.getName());
                });
        RuleDocument ruleDocument = ruleMapper.toDocument(createDTO);
        RuleDocument savedRule = ruleDocumentRepository.save(ruleDocument);
        return ruleMapper.toReadDto(savedRule);
    }

    @Override
    public RuleReadDTO updateRule(String id, RuleUpdateDTO updateDto) {
        return ruleDocumentRepository.findById(id)
                .map(existingRule -> {
                    ruleMapper.update(existingRule, updateDto);
                    return existingRule;
                })
                .map(ruleDocumentRepository::save)
                .map(ruleMapper::toReadDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteRule(String id) {
        ruleDocumentRepository.deleteById(id);
    }
}
