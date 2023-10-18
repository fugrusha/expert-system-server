package com.holovko.expertsystem.dao;

import com.holovko.expertsystem.model.dto.CityReadDTO;

import java.util.List;

public interface CityDao {

    List<CityReadDTO> getAllCities();
}
