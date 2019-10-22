package ru.yamal.barbos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yamal.barbos.domain.model.DiseaseEntity;

import java.util.Optional;

public interface DiseaseRepository extends JpaRepository<DiseaseEntity, Long> {
    Optional<DiseaseEntity> findById(Long id);
}
