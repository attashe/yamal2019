package ru.yamal.barbos.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yamal.barbos.domain.model.DiseaseEntity;
import ru.yamal.barbos.domain.repository.DiseaseRepository;
import ru.yamal.barbos.dto.DiseaseDto;
import ru.yamal.barbos.exception.CustomException;

@Transactional
@RequiredArgsConstructor
@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;
    private final ModelMapper modelMapper;

    public DiseaseDto updateDisease(DiseaseDto diseaseDto) {
        DiseaseEntity diseaseEntity = diseaseRepository.findById(diseaseDto.getId()).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        diseaseEntity.setDescription(diseaseDto.getDescription());
        diseaseEntity.setDiseaseStatus(diseaseDto.getDiseaseStatus());
        diseaseEntity.setLocalDate(diseaseDto.getLocalDate());
        DiseaseEntity saved = diseaseRepository.save(diseaseEntity);
        return modelMapper.map(saved, DiseaseDto.class);
    }

}
