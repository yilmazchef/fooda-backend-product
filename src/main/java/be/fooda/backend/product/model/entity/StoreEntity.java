package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Indexed
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    UUID id;

    @FullTextField
    @Length(min = 2)
    String name;

    @KeywordField
    @Column(columnDefinition = "BINARY(16)")
    UUID eStoreId;

    @OneToOne
    @ToString.Exclude
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoreEntity)) return false;
        StoreEntity that = (StoreEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
