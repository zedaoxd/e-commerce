package com.api.ecommerce.models;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, length = 80)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 80)
    private String street;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createDate;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updateDate;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityModel city;

    @OneToMany(mappedBy = "person", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Setter(AccessLevel.NONE)
    private List<RolePersonModel> rolePersons = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        createDate = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = Instant.now();
    }

    public void setRolePersons(List<RolePersonModel> roles) {
        for (RolePersonModel role : roles) {
            role.setPerson(this);
        }
        this.rolePersons.clear();
        this.rolePersons.addAll(roles);
    }

}
