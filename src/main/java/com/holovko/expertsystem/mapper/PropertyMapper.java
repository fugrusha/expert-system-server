package com.holovko.expertsystem.mapper;

import com.holovko.expertsystem.model.document.PropertyDocument;
import com.holovko.expertsystem.model.dto.property.PropertyCreateDTO;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.model.dto.property.PropertyUpdateDTO;
import com.holovko.expertsystem.model.dto.propertyfeature.PropertyFeatureReadDTO;
import com.holovko.expertsystem.model.entity.ImageEntity;
import com.holovko.expertsystem.model.entity.PropertyEntity;
import com.holovko.expertsystem.model.entity.PropertyFeatureEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PropertyMapper {

    @Mapping(target = "sellerId", source = "entity.seller.id")
    @Mapping(target = "city", source = "entity.city.name")
    PropertyReadDTO toReadDTO(PropertyEntity entity);

    PropertyReadDTO toReadDTO(PropertyDocument propertyDocument);

    @Mapping(target = "sellerId", source = "entity.seller.id")
    @Mapping(target = "city", source = "entity.city.name")
    @Mapping(target = "images", source = "imageEntities")
    @Mapping(target = "features", source = "propertyFeatureEntities")
    PropertyReadDTO toReadDTO(PropertyEntity entity, List<PropertyFeatureEntity> propertyFeatureEntities, List<ImageEntity> imageEntities);

    @Mapping(target = "city", ignore = true)
    PropertyEntity toEntity(PropertyCreateDTO createDTO);

    PropertyDocument toDocument(PropertyCreateDTO createDTO);

    @Mapping(target = "city", ignore = true)
    void updateEntity(@MappingTarget PropertyEntity entity, PropertyUpdateDTO updateDTO);

    void updateDocument(@MappingTarget PropertyDocument document, PropertyUpdateDTO updateDTO);

    default List<PropertyFeatureReadDTO> mapFeaturesFromDocument(List<String> propertyFeatures) {
        if (propertyFeatures == null) {
            return List.of();
        }
        return propertyFeatures.stream()
                .map(propertyFeature -> {
                    PropertyFeatureReadDTO dto = new PropertyFeatureReadDTO();
                    dto.setFeatureName(propertyFeature);
                    return dto;
                }).toList();
    }

    default List<PropertyDocument.Image> mapImagesToDocument(List<String> imgUrls) {
        if (imgUrls == null) {
            return List.of();
        }
        return imgUrls.stream()
                .map(url -> {
                    PropertyDocument.Image image = new PropertyDocument.Image();
                    image.setImageURL(url);
                    return image;
                }).toList();
    }
}
