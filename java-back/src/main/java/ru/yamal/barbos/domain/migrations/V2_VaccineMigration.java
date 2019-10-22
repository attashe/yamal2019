package ru.yamal.barbos.domain.migrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yamal.barbos.domain.model.VaccineEntity;
import ru.yamal.barbos.domain.repository.VaccineRepository;

@Transactional
@RequiredArgsConstructor
@Component
public class V2_VaccineMigration implements Migration {

    private final VaccineRepository vaccineRepository;

    @Override
    public void migrate() {
        vaccineRepository.save(new VaccineEntity("Vaccine 1", "some long long long description 1"));
        vaccineRepository.save(new VaccineEntity("Vaccine 2", "some long long long description 2"));
        vaccineRepository.save(new VaccineEntity("Vaccine 3", "some long long long description 3"));
    }
}
