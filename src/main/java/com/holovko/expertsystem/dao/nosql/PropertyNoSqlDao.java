package com.holovko.expertsystem.dao.nosql;

import com.holovko.expertsystem.dao.PropertyDao;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.PropertyMapper;
import com.holovko.expertsystem.model.document.PropertyDocument;
import com.holovko.expertsystem.model.document.UserInfoDocument;
import com.holovko.expertsystem.model.dto.property.PropertyCreateDTO;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.model.dto.property.PropertyUpdateDTO;
import com.holovko.expertsystem.model.entity.PropertyStatus;
import com.holovko.expertsystem.repository.mongo.PropertyDocumentRepository;
import com.holovko.expertsystem.repository.mongo.UserInfoDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Profile("mongo")
@Component
@RequiredArgsConstructor
public class PropertyNoSqlDao implements PropertyDao {

    private final PropertyDocumentRepository propertyRepository;
    private final UserInfoDocumentRepository userInfoRepository;
    private final PropertyMapper propertyMapper;

    @Override
    public Optional<PropertyReadDTO> findById(String id) {
        return propertyRepository.findById(id)
                .map(propertyMapper::toReadDTO);
    }

    @Override
    public List<PropertyReadDTO> findAll(String search) {
        List<PropertyDocument> propertyDocuments = search == null || search.trim().isEmpty()
                ? propertyRepository.findAllByStatus(PropertyStatus.FOR_SALE)
                : propertyRepository.findWithSearch(search.toLowerCase());

        return propertyDocuments.stream()
                .map(propertyMapper::toReadDTO)
                .toList();
    }

    @Override
    public PropertyReadDTO create(String sellerId, PropertyCreateDTO createDTO) {
        UserInfoDocument seller = userInfoRepository.findById(sellerId)
                .orElseThrow(EntityNotFoundException::new);

        PropertyDocument propertyEntity = propertyMapper.toDocument(createDTO);

        propertyEntity.setSellerId(seller.getId());
        propertyEntity.setStatus(PropertyStatus.FOR_SALE);
        PropertyDocument saved = propertyRepository.save(propertyEntity);

        return propertyMapper.toReadDTO(saved);
    }

    @Override
    public PropertyReadDTO update(String id, PropertyUpdateDTO updateDTO) {
        return propertyRepository.findById(id)
                .map(document -> {
                    propertyMapper.updateDocument(document, updateDTO);
                    return document;
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
