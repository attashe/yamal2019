package ru.yamal.barbos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.NotAudited;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
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
