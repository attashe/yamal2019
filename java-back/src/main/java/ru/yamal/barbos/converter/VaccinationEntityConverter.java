package ru.yamal.barbos.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yamal.barbos.domain.model.VaccinationEntity;
import ru.yamal.barbos.dto.VaccinationDto;

@RequiredArgsConstructor
@Transactional
@Component
public class VaccinationEntityConverter implements Converter<VaccinationEntity, VaccinationDto> {
    @Override
    public VaccinationDto convert(MappingContext<VaccinationEntity, VaccinationDto> context) {
        return new VaccinationDto(
                context.getSource().getVaccine().getVaccineName(),
                context.getSource().getVaccine().getDescription(),
                context.getSource().getVaccinationDate()
        );
    }
}
