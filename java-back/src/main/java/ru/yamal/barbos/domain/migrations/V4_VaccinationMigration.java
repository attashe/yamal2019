package ru.yamal.barbos.domain.migrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yamal.barbos.domain.model.AnimalEntity;
import ru.yamal.barbos.domain.model.VaccinationEntity;
import ru.yamal.barbos.domain.repository.AnimalRepository;
import ru.yamal.barbos.domain.repository.VaccinationRepository;
import ru.yamal.barbos.domain.repository.VaccineRepository;

import java.time.LocalDate;

@Transactional
@RequiredArgsConstructor
@Component
public class V4_VaccinationMigration implements Migration {

    private final VaccinationRepository vaccinationRepository;
    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;

    @Override
    public void migrate() {
        AnimalEntity animalEntity = animalRepository.findById(1L).get();
        vaccinationRepository.save(new VaccinationEntity(vaccineRepository.findOne(1L), animalEntity, LocalDate.now()));
        vaccinationRepository.save(new VaccinationEntity(vaccineRepository.findOne(2L), animalEntity, LocalDate.now()));
    }
}
