package com.holovko.expertsystem.dao;

import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;

import java.util.List;

public interface FavoritePropertyDao {
    List<PropertyReadDTO> getAllFavorites(String userId);

    void addToFavorites(String userId, String propertyId);

    void removeFromFavorites(String userId, String propertyId);
}
