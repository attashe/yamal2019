package ru.yamal.barbos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yamal.barbos.domain.model.AnimalStatus;
import ru.yamal.barbos.domain.model.AnimalType;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAnimalDto {
    private Long id;

    private String name;

    private Boolean gender; // false == female

    private AnimalType animalType;

    private LocalDate receivedDate;

    private LocalDate birthDate;

    private AnimalStatus animalStatus;

    private LocalDate lastDeWormingDate;

    private String description;
}
