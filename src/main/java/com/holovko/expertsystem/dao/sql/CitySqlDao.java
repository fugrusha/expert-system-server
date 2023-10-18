package com.holovko.expertsystem.dao.sql;

import com.holovko.expertsystem.dao.CityDao;
import com.holovko.expertsystem.mapper.CityMapper;
import com.holovko.expertsystem.model.dto.CityReadDTO;
import com.holovko.expertsystem.repository.jpa.CityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CitySqlDao implements CityDao {

    private final CityJpaRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityReadDTO> getAllCities() {
        return cityRepository.findAll().stream()
                .map(cityMapper::toReadDto)
                .toList();
    }
}
