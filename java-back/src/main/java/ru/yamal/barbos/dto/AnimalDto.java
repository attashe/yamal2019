package ru.yamal.barbos.dto;

import lombok.Data;
import ru.yamal.barbos.domain.model.AnimalType;

import java.time.LocalDate;

@Data
public class AnimalDto {

    private Long id;

    private String name;

    private Boolean gender; // false == female

    private AnimalType animalType;

    private LocalDate receivedDate;

    private LocalDate birthDate;
}
