package ru.yamal.barbos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yamal.barbos.domain.model.OwnerEntity;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {
}
