package ru.yamal.barbos.domain.migrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yamal.barbos.domain.model.AnimalEntity;
import ru.yamal.barbos.domain.model.DiseaseEntity;
import ru.yamal.barbos.domain.model.DiseaseStatus;
import ru.yamal.barbos.domain.repository.AnimalRepository;
import ru.yamal.barbos.domain.repository.DiseaseRepository;
import ru.yamal.barbos.exception.CustomException;

import java.time.LocalDate;

@Transactional
@RequiredArgsConstructor
@Component
public class V5_DiseaseMigration implements Migration {

    private final DiseaseRepository diseaseRepository;
    private final AnimalRepository animalRepository;

    @Override
    public void migrate() {
        AnimalEntity animalEntity = animalRepository.findById(1L).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        diseaseRepository.save(new DiseaseEntity(
                "Слишком энергичный из-за китикет",
                "",
                LocalDate.now(),
                DiseaseStatus.ACTIVE,
                animalEntity
        ));
    }
}
