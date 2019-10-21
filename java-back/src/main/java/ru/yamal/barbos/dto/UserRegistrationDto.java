package ru.yamal.barbos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yamal.barbos.domain.model.Role;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegistrationDto {
    private String phoneNumber;
    private String password;
    private List<Role> roles;
    private String firstName;
    private String lastName;
    private String surname;
    private String email;
}
