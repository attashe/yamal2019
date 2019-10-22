package ru.yamal.barbos.domain.model;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Audited
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"vaccinations"})
@ToString(exclude = {"vaccinations"})
@Entity
public class AnimalEntity extends BaseEntity<Long> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean gender; // false == female

    @Enumerated(EnumType.STRING)
    private AnimalType animalType;

    @Column
    private LocalDate receivedDate;

    @Column
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private AnimalStatus animalStatus;

    @Column
    private LocalDate lastDeWormingDate;

    @Column(nullable = false)
    private Boolean sterilized;

    @Column
    private LocalDate sterilizationDate;

    @Column
    private String description;

    @ElementCollection
    private List<String> photoIds = new ArrayList<>();

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private Set<VaccinationEntity> vaccinations = new HashSet<>();
}
