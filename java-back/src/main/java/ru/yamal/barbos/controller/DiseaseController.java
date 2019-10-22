package ru.yamal.barbos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yamal.barbos.dto.DiseaseDto;
import ru.yamal.barbos.service.DiseaseService;

@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HELPER')")
@RequiredArgsConstructor
@RequestMapping("/diseases")
@RestController
public class DiseaseController {

    private final DiseaseService diseaseService;

    @PutMapping("/{id}")
    public DiseaseDto updateDiseaseStatus(@RequestBody DiseaseDto diseaseDto) {
        return diseaseService.updateDisease(diseaseDto);
    }
}
