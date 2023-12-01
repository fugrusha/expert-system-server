package com.holovko.expertsystem.dao.sql;

import com.holovko.expertsystem.dao.PropertyDao;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.ImageMapper;
import com.holovko.expertsystem.mapper.PropertyFeatureMapper;
import com.holovko.expertsystem.mapper.PropertyMapper;
import com.holovko.expertsystem.model.dto.property.PropertyCreateDTO;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.model.dto.property.PropertyUpdateDTO;
import com.holovko.expertsystem.model.entity.*;
import com.holovko.expertsystem.repository.jpa.*;
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
    private final ImageJpaRepository imageRepository;
    private final PropertyFeatureJpaRepository propertyFeatureRepository;
    private final PropertyMapper propertyMapper;
    private final ImageMapper imageMapper;
    private final PropertyFeatureMapper propertyFeatureMapper;

    @Override
    public Optional<PropertyReadDTO> findById(String id) {
        List<PropertyFeatureEntity> propertyFeatures = propertyFeatureRepository.findAllByPropertyId(id);
        List<ImageEntity> images = imageRepository.findByPropertyId(id);
        return propertyRepository.findById(id)
                .map(property -> propertyMapper.toReadDTO(property, propertyFeatures, images));
    }

    @Override
    public List<PropertyReadDTO> findAll(String search) {
        List<PropertyEntity> entities = search == null || search.trim().isEmpty()
                ? propertyRepository.findAllByStatus(PropertyStatus.FOR_SALE)
                : propertyRepository.findWithSearch(search.toLowerCase());

        return entities.stream()
                .map(propertyEntity -> {
                    List<ImageEntity> images = imageRepository.findByPropertyId(propertyEntity.getId());
                    List<PropertyFeatureEntity> propertyFeatures = propertyFeatureRepository.findAllByPropertyId(propertyEntity.getId());
                    return propertyMapper.toReadDTO(propertyEntity, propertyFeatures, images);
                })
                .toList();
    }

    @Override
    public PropertyReadDTO create(String sellerId, PropertyCreateDTO createDTO) {
        UserInfoEntity seller = userInfoRepository.findById(sellerId)
                .orElseThrow(EntityNotFoundException::new);
        CityEntity cityEntity = cityRepository.findByName(createDTO.getCity())
                .orElseThrow(EntityNotFoundException::new);

        PropertyEntity propertyEntity = propertyMapper.toEntity(createDTO);

        propertyEntity.setSeller(seller);
        propertyEntity.setCity(cityEntity);
        propertyEntity.setStatus(PropertyStatus.FOR_SALE);
        PropertyEntity saved = propertyRepository.save(propertyEntity);

        if (!createDTO.getImages().isEmpty()) {
            List<ImageEntity> imageEntities = createDTO.getImages().stream()
                    .map(imageUrl -> imageMapper.toEntity(imageUrl, saved))
                    .toList();
            List<ImageEntity> savedImages = imageRepository.saveAll(imageEntities);
        }

        if (!createDTO.getFeatures().isEmpty()) {
            List<PropertyFeatureEntity> featureEntities = createDTO.getFeatures().stream()
                    .map(feature -> propertyFeatureMapper.toEntity(feature, saved))
                    .toList();
            propertyFeatureRepository.saveAll(featureEntities);
        }

        return propertyMapper.toReadDTO(saved);
    }

    @Override
    public PropertyReadDTO update(String id, PropertyUpdateDTO updateDTO) {
        return propertyRepository.findById(id)
                .map(entity -> {
                    propertyMapper.updateEntity(entity, updateDTO);

                    if (updateDTO.getCity() != null) {
                        CityEntity cityEntity = cityRepository.findByName(updateDTO.getCity())
                                .orElseThrow(EntityNotFoundException::new);
                        entity.setCity(cityEntity);
                    }

                    if (!updateDTO.getImages().isEmpty()) {
                        List<ImageEntity> imageEntities = updateDTO.getImages().stream()
                                .map(imageUrl -> imageMapper.toEntity(imageUrl, entity))
                                .toList();
                        imageRepository.saveAll(imageEntities);
                    }

                    if (!updateDTO.getFeatures().isEmpty()) {
                        List<PropertyFeatureEntity> featureEntities = updateDTO.getFeatures().stream()
                                .map(feature -> propertyFeatureMapper.toEntity(feature, entity))
                                .toList();
                        propertyFeatureRepository.saveAll(featureEntities);
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
