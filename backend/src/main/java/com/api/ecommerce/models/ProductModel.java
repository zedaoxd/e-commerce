package com.api.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String shortDescription;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String longDescription;

    @Column(nullable = false)
    private Double costValue;

    @Column(nullable = false)
    private Double seleValue;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandModel brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

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
