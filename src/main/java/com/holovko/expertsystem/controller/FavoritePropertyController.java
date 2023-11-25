package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.favorite.FavoritePropertyCreateDTO;
import com.holovko.expertsystem.model.dto.property.PropertyReadDTO;
import com.holovko.expertsystem.service.FavoritePropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users/{userId}/favorites")
@RequiredArgsConstructor
public class FavoritePropertyController {

    private final FavoritePropertyService favoritePropertyService;

    @GetMapping
    public List<PropertyReadDTO> getAllFavorites(@PathVariable String userId) {
        log.info("getAllFavorites: userId={}", userId);
        return favoritePropertyService.getAllFavorites(userId);
    }

    @PostMapping
    public ResponseEntity<Void> addToFavorites(
            @PathVariable String userId,
            @RequestBody FavoritePropertyCreateDTO createDTO
    ) {
        log.info("addToFavorites: userId={}, propertyId={}", userId, createDTO.getPropertyId());
        favoritePropertyService.addToFavorites(userId, createDTO.getPropertyId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{propertyId}")
    public void removeFromFavorites(
            @PathVariable String userId,
            @PathVariable String propertyId
    ) {
        log.info("removeFromFavorites: userId={}, propertyId={}", userId, propertyId);
        favoritePropertyService.removeFromFavorites(userId, propertyId);
    }
}
