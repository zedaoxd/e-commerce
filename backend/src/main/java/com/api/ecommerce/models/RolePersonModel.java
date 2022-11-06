package com.api.ecommerce.models;

import com.api.ecommerce.dtos.RolePersonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_role_person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RolePersonModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonModel person;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleModel role;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createDate;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updateDate;

    @PrePersist
    public void prePersist() {
        createDate = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = Instant.now();
    }
}
