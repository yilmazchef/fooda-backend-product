package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Indexed
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    UUID id;

    @FullTextField
    String title;

    @GenericField
    BigDecimal amount;

    @GenericField
    LocalTime expiryTime;

    @GenericField
    LocalDate expiryDate;

    Boolean isDefault;

    @FullTextField
    String currency; // EURO, €, EUR -> EUR

    @ManyToOne
    @ToString.Exclude
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceEntity)) return false;
        PriceEntity that = (PriceEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
