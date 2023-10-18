package com.holovko.expertsystem.service.sql;

import com.holovko.expertsystem.exception.EntityAlreadyExistsException;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.RuleMapper;
import com.holovko.expertsystem.model.dto.rule.RuleCreateDTO;
import com.holovko.expertsystem.model.dto.rule.RuleReadDTO;
import com.holovko.expertsystem.model.dto.rule.RuleUpdateDTO;
import com.holovko.expertsystem.model.entity.RuleEntity;
import com.holovko.expertsystem.repository.jpa.RuleJpaRepository;
import com.holovko.expertsystem.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleSqlService implements RuleService {

    private final RuleJpaRepository ruleJpaRepository;
    private final RuleMapper ruleMapper;

    @Override
    public RuleReadDTO getRule(String id) {
        return ruleJpaRepository.findById(Long.valueOf(id))
                .map(ruleMapper::toReadDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<RuleReadDTO> getRules() {
        return ruleJpaRepository.findAll()
                .stream()
                .map(ruleMapper::toReadDto)
                .toList();
    }

    @Override
    public RuleReadDTO createRule(RuleCreateDTO createDTO) {
        ruleJpaRepository.findByName(createDTO.getName())
                .ifPresent(rule -> {
                    throw new EntityAlreadyExistsException(rule.getName());
                });
        RuleEntity entity = ruleMapper.toEntity(createDTO);
        RuleEntity savedRule = ruleJpaRepository.save(entity);
        return ruleMapper.toReadDto(savedRule);
    }

    @Override
    public RuleReadDTO updateRule(String id, RuleUpdateDTO updateDto) {
        return ruleJpaRepository.findById(Long.valueOf(id))
                .map(existingRule -> {
                    ruleMapper.update(existingRule, updateDto);
                    return existingRule;
                })
                .map(ruleJpaRepository::save)
                .map(ruleMapper::toReadDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteRule(String id) {
        ruleJpaRepository.deleteById(Long.valueOf(id));
    }
}
