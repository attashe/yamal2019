package ru.yamal.barbos.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OwnerDto {
    private String fio;

    private String phone;

    private String orderNumber;

    private LocalDate orderDate;
}
