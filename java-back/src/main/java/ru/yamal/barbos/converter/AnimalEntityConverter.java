package ru.yamal.barbos.converter;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yamal.barbos.domain.model.AnimalEntity;
import ru.yamal.barbos.dto.AnimalDto;
import ru.yamal.barbos.dto.VaccinationDto;

import java.util.stream.Collectors;

@Transactional
@Component
public class AnimalEntityConverter implements Converter<AnimalEntity, AnimalDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public AnimalEntityConverter(@Lazy ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AnimalDto convert(MappingContext<AnimalEntity, AnimalDto> context) {
        return new AnimalDto(
                context.getSource().getId(),
                context.getSource().getName(),
                context.getSource().getGender(),
                context.getSource().getAnimalType(),
                context.getSource().getReceivedDate(),
                context.getSource().getBirthDate(),
                context.getSource().getAnimalStatus(),
                context.getSource().getLastDeWormingDate(),
                context.getSource().getSterilized(),
                context.getSource().getSterilizationDate(),
                context.getSource().getDescription(),
                context.getSource().getPhotoIds(),
                context.getSource().getVaccinations().stream().map(vaccinationEntity -> modelMapper.map(vaccinationEntity, VaccinationDto.class)).collect(Collectors.toSet())
        );
    }
}
