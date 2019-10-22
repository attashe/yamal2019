package ru.yamal.barbos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yamal.barbos.domain.model.AnimalStatus;
import ru.yamal.barbos.domain.model.AnimalType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnimalDto {

    private Long id;

    private String name;

    private Boolean gender; // false == female

    private AnimalType animalType;

    private LocalDate receivedDate;

    private LocalDate birthDate;

    private AnimalStatus animalStatus;

    private LocalDate lastDeWormingDate;

    private Boolean sterilized;

    private LocalDate sterilizationDate;

    private String description;

    private List<String> photoIds = new ArrayList<>();

    private Set<VaccinationDto> vaccinations = new HashSet<>();

    private Set<DiseaseDto> diseases = new HashSet<>();

    private OwnerDto ownerDto;
}
