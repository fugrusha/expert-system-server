package com.holovko.expertsystem.dao.nosql;

import com.holovko.expertsystem.dao.CityDao;
import com.holovko.expertsystem.mapper.CityMapper;
import com.holovko.expertsystem.model.dto.CityReadDTO;
import com.holovko.expertsystem.repository.mongo.CityDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("mongo")
@Component
@RequiredArgsConstructor
public class CityNoSqlDao implements CityDao {

    private final CityDocumentRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityReadDTO> getAllCities() {
        return cityRepository.findAll().stream()
                .map(cityMapper::toReadDto)
                .toList();
    }
}
