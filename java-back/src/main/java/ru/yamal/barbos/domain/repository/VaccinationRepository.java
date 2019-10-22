package ru.yamal.barbos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yamal.barbos.domain.model.VaccinationEntity;

public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long> {
}
