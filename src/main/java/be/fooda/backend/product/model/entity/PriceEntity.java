package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

// LOMBOK
@Table(name = "PriceEntity")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)

// JPA
@Entity

public class PriceEntity {

    @Column(nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;


    @FullTextField
    String title;

    @GenericField
    @PositiveOrZero
    BigDecimal amount;

    @GenericField
    LocalTime expiryTime;

    @GenericField
    @FutureOrPresent
    LocalDate expiryDate;

    @ColumnDefault(value = "FALSE")
    Boolean isDefault;

    @FullTextField
    @ColumnDefault(value = "EUR")
    String currency; // EURO, €, EUR -> EUR

    @JoinColumn
    @ManyToOne(cascade = {CascadeType.PERSIST})
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PriceEntity)) {
            return false;
        }
        PriceEntity that = (PriceEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
