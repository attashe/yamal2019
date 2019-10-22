package ru.yamal.barbos.converter;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yamal.barbos.domain.model.OwnerEntity;
import ru.yamal.barbos.dto.OwnerDto;

@Transactional
@Component
public class OwnerEntityConverter implements Converter<OwnerEntity, OwnerDto> {
    @Override
    public OwnerDto convert(MappingContext<OwnerEntity, OwnerDto> context) {
        return new OwnerDto(
                context.getSource().getFio(),
                context.getSource().getPhone(),
                context.getSource().getOrderNumber(),
                context.getSource().getOrderDate()
        );
    }
}
