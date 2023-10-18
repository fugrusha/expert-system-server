package com.holovko.expertsystem.service;

import com.holovko.expertsystem.dao.CityDao;
import com.holovko.expertsystem.model.dto.CityReadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityDao cityDao;

    public List<CityReadDTO> getCities() {
        return cityDao.getAllCities();
    }
}
