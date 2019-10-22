package ru.yamal.barbos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yamal.barbos.domain.model.DiseaseStatus;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiseaseDto {

    Long id;

    private String description;

    private LocalDate localDate;

    private DiseaseStatus diseaseStatus;

}
