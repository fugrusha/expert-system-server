package com.holovko.expertsystem.dao.sql;

import com.holovko.expertsystem.dao.FavoritePropertyDao;
import com.holovko.expertsystem.exception.EntityAlreadyExistsException;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.PropertyMapper;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.model.entity.PropertyEntity;
import com.holovko.expertsystem.model.entity.PropertyFavoriteEntity;
import com.holovko.expertsystem.model.entity.UserInfoEntity;
import com.holovko.expertsystem.repository.jpa.PropertyFavoriteJpaRepository;
import com.holovko.expertsystem.repository.jpa.PropertyJpaRepository;
import com.holovko.expertsystem.repository.jpa.UserInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FavoritePropertySqlDao implements FavoritePropertyDao {

    private final PropertyFavoriteJpaRepository propertyFavoriteRepository;
    private final PropertyJpaRepository propertyRepository;
    private final UserInfoJpaRepository userInfoRepository;
    private final PropertyMapper propertyMapper;

    @Override
    public List<PropertyReadDTO> getAllFavorites(String userId) {
        List<PropertyFavoriteEntity> favoriteEntities = propertyFavoriteRepository.findAllByBuyerId(userId);
        return favoriteEntities.stream()
                .map(PropertyFavoriteEntity::getProperty)
                .map(propertyMapper::toReadDTO)
                .toList();
    }

    @Transactional
    @Override
    public void addToFavorites(String userId, String propertyId) {
        propertyFavoriteRepository
                .findByBuyerIdAndPropertyId(userId, propertyId)
                .ifPresent(favorite -> {
                    throw new EntityAlreadyExistsException("Favorite already added");
                });

        PropertyEntity propertyEntity = propertyRepository.findById(propertyId)
                .orElseThrow(EntityNotFoundException::new);

        UserInfoEntity userInfoEntity = userInfoRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);

        PropertyFavoriteEntity entity = new PropertyFavoriteEntity();
        entity.setProperty(propertyEntity);
        entity.setBuyer(userInfoEntity);
        PropertyFavoriteEntity saved = propertyFavoriteRepository.save(entity);
    }

    @Transactional
    @Override
    public void removeFromFavorites(String userId, String propertyId) {
        PropertyFavoriteEntity favorite = propertyFavoriteRepository
                .findByBuyerIdAndPropertyId(userId, propertyId)
                .orElseThrow(EntityNotFoundException::new);

        propertyFavoriteRepository.delete(favorite);
    }
}
