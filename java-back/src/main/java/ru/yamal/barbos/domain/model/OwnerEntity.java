package ru.yamal.barbos.domain.model;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Audited
@Data
@EqualsAndHashCode(callSuper = true, exclude = "animals")
@ToString(exclude = "animals")
@Entity
public class OwnerEntity extends BaseEntity<Long> {
    @Column
    private String fio;

    @Column
    private String phone;

    @Column
    private String orderNumber;

    @Column
    private LocalDate orderDate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<AnimalEntity> animals = new HashSet<>();

}
