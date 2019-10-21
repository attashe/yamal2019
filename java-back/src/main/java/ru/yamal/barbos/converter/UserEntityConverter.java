package ru.yamal.barbos.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yamal.barbos.domain.model.UserEntity;
import ru.yamal.barbos.dto.UserDto;

@RequiredArgsConstructor
@Transactional
@Component
public class UserEntityConverter implements Converter<UserEntity, UserDto> {

    @Override
    public UserDto convert(MappingContext<UserEntity, UserDto> context) {
        return new UserDto(
                context.getSource().getId(),
                context.getSource().getPhoneNumber(),
                context.getSource().getRoles(),
                context.getSource().getFirstName(),
                context.getSource().getLastName(),
                context.getSource().getSurname()
        );
    }
}
