package com.holovko.expertsystem.service;

import com.holovko.expertsystem.dao.FavoritePropertyDao;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritePropertyService {

    private final FavoritePropertyDao favoritePropertyDao;

    public List<PropertyReadDTO> getAllFavorites(String userId) {
        return favoritePropertyDao.getAllFavorites(userId);
    }

    public void addToFavorites(String userId, String propertyId) {
        favoritePropertyDao.addToFavorites(userId, propertyId);
    }

    public void removeFromFavorites(String userId, String favoriteId) {
        favoritePropertyDao.removeFromFavorites(userId, favoriteId);
    }
}
