package ru.yamal.barbos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yamal.barbos.domain.model.AnimalEntity;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
    Optional<AnimalEntity> findById(Long id);
}
