package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

// LOMBOK
@Table(name = "TaxEntity")
@Getter
@Setter
@ToString
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)

// JPA
@Entity

public class TaxEntity {

    @Column(nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;


    @Column(nullable = false, unique = true)
    @FullTextField
    String title;

    @GenericField
    @Range(min = 0, max = 100)
    Double percentage;

    Boolean isDefault;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxEntity)) return false;
        final var taxEntity = (TaxEntity) o;
        return Objects.equals(getId(), taxEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
