package com.holovko.expertsystem.dao.nosql;

import com.holovko.expertsystem.dao.FavoritePropertyDao;
import com.holovko.expertsystem.exception.EntityAlreadyExistsException;
import com.holovko.expertsystem.exception.EntityNotFoundException;
import com.holovko.expertsystem.mapper.PropertyMapper;
import com.holovko.expertsystem.model.document.PropertyDocument;
import com.holovko.expertsystem.model.document.PropertyFavoriteDocument;
import com.holovko.expertsystem.model.document.UserInfoDocument;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.repository.mongo.PropertyDocumentRepository;
import com.holovko.expertsystem.repository.mongo.PropertyFavoriteDocumentRepository;
import com.holovko.expertsystem.repository.mongo.UserInfoDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Profile("mongo")
@Component
@RequiredArgsConstructor
public class FavoritePropertyNoSqlDao implements FavoritePropertyDao {

    private final PropertyFavoriteDocumentRepository propertyFavoriteRepository;
    private final PropertyDocumentRepository propertyRepository;
    private final UserInfoDocumentRepository userInfoRepository;
    private final PropertyMapper propertyMapper;

    @Override
    public List<PropertyReadDTO> getAllFavorites(String userId) {
        List<PropertyFavoriteDocument> favorites = propertyFavoriteRepository.findAllByBuyerId(userId);
        return favorites.stream()
                .map(favorite -> propertyRepository.findById(favorite.getPropertyId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(propertyMapper::toReadDTO)
                .toList();
    }

    @Override
    public void addToFavorites(String userId, String propertyId) {
        propertyFavoriteRepository
                .findByBuyerIdAndPropertyId(userId, propertyId)
                .ifPresent(favorite -> {
                    throw new EntityAlreadyExistsException("Favorite already added");
                });

        PropertyDocument propertyDocument = propertyRepository.findById(propertyId)
                .orElseThrow(EntityNotFoundException::new);

        UserInfoDocument userInfoDocument = userInfoRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);

        PropertyFavoriteDocument document = new PropertyFavoriteDocument();
        document.setPropertyId(propertyDocument.getId());
        document.setBuyerId(userInfoDocument.getId());
        PropertyFavoriteDocument saved = propertyFavoriteRepository.save(document);
    }

    @Override
    public void removeFromFavorites(String userId, String propertyId) {
        PropertyFavoriteDocument favorite = propertyFavoriteRepository
                .findByBuyerIdAndPropertyId(userId, propertyId)
                .orElseThrow(EntityNotFoundException::new);

        propertyFavoriteRepository.delete(favorite);
    }
}
