package ru.yamal.barbos.domain.migrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yamal.barbos.domain.model.AnimalEntity;
import ru.yamal.barbos.domain.model.AnimalStatus;
import ru.yamal.barbos.domain.model.AnimalType;
import ru.yamal.barbos.domain.repository.AnimalRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

@RequiredArgsConstructor
@Transactional
@Component
public class V3_AnimalMigration implements Migration {

    private final AnimalRepository animalRepository;

    @Override
    public void migrate() {
        AnimalEntity boris = animalRepository.save(new AnimalEntity("Boris", true, AnimalType.CAT, LocalDate.now(), LocalDate.now(), AnimalStatus.IN_SHELTER, null, false, null, "В чем секрет кота Бориса? Китикет - это ЭНЕРГИЯ КОТА!", new ArrayList<>(), new HashSet<>(), new HashSet<>()));
    }
}
