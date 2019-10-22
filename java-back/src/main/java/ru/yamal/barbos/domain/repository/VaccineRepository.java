package ru.yamal.barbos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yamal.barbos.domain.model.VaccineEntity;

public interface VaccineRepository extends JpaRepository<VaccineEntity, Long> {
}
