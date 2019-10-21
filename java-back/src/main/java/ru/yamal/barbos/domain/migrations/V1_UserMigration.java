package ru.yamal.barbos.domain.migrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yamal.barbos.domain.model.Role;
import ru.yamal.barbos.dto.UserRegistrationDto;
import ru.yamal.barbos.service.UserService;

import java.util.Collections;

@RequiredArgsConstructor
@Transactional
@Component
public class V1_UserMigration implements Migration {

    private final UserService userService;

    @Override
    public void migrate() {
        UserRegistrationDto adminDto = new UserRegistrationDto();
        adminDto.setPhoneNumber("+70000000001");
        adminDto.setPassword("001");
        adminDto.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        adminDto.setEmail("admin@rsv.ru");
        adminDto.setFirstName("Admin");
        userService.signup(adminDto);

        UserRegistrationDto clientDto = new UserRegistrationDto();
        clientDto.setPhoneNumber("+70000000002");
        clientDto.setPassword("002");
        clientDto.setRoles(Collections.singletonList(Role.ROLE_HELPER));
        clientDto.setEmail("client1@rsv.ru");
        clientDto.setFirstName("Client1");
        userService.signup(clientDto);

        clientDto = new UserRegistrationDto();
        clientDto.setPhoneNumber("+70000000003");
        clientDto.setPassword("003");
        clientDto.setRoles(Collections.singletonList(Role.ROLE_USER));
        clientDto.setEmail("client2@rsv.ru");
        clientDto.setFirstName("Client2");
        userService.signup(clientDto);

        clientDto = new UserRegistrationDto();
        clientDto.setPhoneNumber("+70000000004");
        clientDto.setPassword("004");
        clientDto.setRoles(Collections.singletonList(Role.ROLE_USER));
        clientDto.setEmail("client3@rsv.ru");
        clientDto.setFirstName("Client3");
        userService.signup(clientDto);

    }
}
