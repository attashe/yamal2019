package ru.yamal.barbos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VaccinationDto {
    private String vaccineName;
    private String description;
    private LocalDate vaccinationDate;
}
