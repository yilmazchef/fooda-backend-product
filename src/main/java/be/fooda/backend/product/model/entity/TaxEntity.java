package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

// LOMBOK
@Getter
@Setter
@ToString
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)

// JPA
@Entity
@EntityListeners(AuditingEntityListener.class)

public class TaxEntity extends Auditable<String> {

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
