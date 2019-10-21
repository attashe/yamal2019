package ru.yamal.barbos.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.List;

@Getter
@Setter
@Entity
public class UserEntity extends BaseEntity<Long> {

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String surname;

    @Column
    private String email;

}
