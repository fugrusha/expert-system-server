package com.holovko.expertsystem.controller;

import com.holovko.expertsystem.model.dto.CityReadDTO;
import com.holovko.expertsystem.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityPublicController {

    private final CityService cityService;

    @GetMapping
    public List<CityReadDTO> getCities() {
        log.info("getCities");
        return cityService.getCities();
    }
}
