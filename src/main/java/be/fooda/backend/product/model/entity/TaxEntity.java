package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class TaxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    UUID id;

    @FullTextField
    String title;

    @GenericField
    @Range(min = 0, max = 100)
    Double percentage;

    Boolean isDefault;

    @ManyToOne
    @ToString.Exclude
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxEntity)) return false;
        TaxEntity taxEntity = (TaxEntity) o;
        return Objects.equals(getId(), taxEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
