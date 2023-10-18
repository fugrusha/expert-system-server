package com.holovko.expertsystem.dao.sql;

import com.holovko.expertsystem.dao.PropertyDao;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.PropertyMapper;
import com.holovko.expertsystem.model.dto.property.PropertyCreateDTO;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.model.dto.property.PropertyUpdateDTO;
import com.holovko.expertsystem.model.entity.CityEntity;
import com.holovko.expertsystem.model.entity.PropertyEntity;
import com.holovko.expertsystem.model.entity.UserInfoEntity;
import com.holovko.expertsystem.repository.jpa.CityJpaRepository;
import com.holovko.expertsystem.repository.jpa.PropertyJpaRepository;
import com.holovko.expertsystem.repository.jpa.UserInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Component
@RequiredArgsConstructor
public class PropertySqlDao implements PropertyDao {

    private final PropertyJpaRepository propertyRepository;
    private final UserInfoJpaRepository userInfoRepository;
    private final CityJpaRepository cityRepository;
    private final PropertyMapper propertyMapper;

    @Override
    public Optional<PropertyReadDTO> findById(String id) {
        return propertyRepository.findById(id)
                .map(propertyMapper::toReadDTO);
    }

    @Override
    public List<PropertyReadDTO> findAll() {
        return propertyRepository.findAll().stream()
                .map(propertyMapper::toReadDTO)
                .toList();
    }

    @Override
    public PropertyReadDTO create(String sellerId, PropertyCreateDTO createDTO) {
        UserInfoEntity seller = userInfoRepository.findById(sellerId)
                .orElseThrow(EntityNotFoundException::new);
        CityEntity cityEntity = cityRepository.findById(createDTO.getCityId())
                .orElseThrow(EntityNotFoundException::new);

        PropertyEntity propertyEntity = propertyMapper.toEntity(createDTO);

        propertyEntity.setSeller(seller);
        propertyEntity.setCity(cityEntity);
        PropertyEntity saved = propertyRepository.save(propertyEntity);
        return propertyMapper.toReadDTO(saved);
    }

    @Override
    public PropertyReadDTO update(String id, PropertyUpdateDTO updateDTO) {
        return propertyRepository.findById(id)
                .map(entity -> {
                    propertyMapper.updateEntity(entity, updateDTO);

                    if (updateDTO.getCityId() != null) {
                        CityEntity cityEntity = cityRepository.findById(updateDTO.getCityId())
                                .orElseThrow(EntityNotFoundException::new);
                        entity.setCity(cityEntity);
                    }
                    return entity;
                })
                .map(propertyRepository::save)
                .map(propertyMapper::toReadDTO)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteById(String id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public List<PropertyReadDTO> getPropertiesBySellerId(String sellerId) {
        return propertyRepository.findAllBySellerId(sellerId).stream()
                .map(propertyMapper::toReadDTO)
                .toList();
    }
}
