package ru.yamal.barbos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yamal.barbos.domain.model.VaccineEntity;

import java.util.Optional;

public interface VaccineRepository extends JpaRepository<VaccineEntity, Long> {

    Optional<VaccineEntity> findById(Long id);
}
