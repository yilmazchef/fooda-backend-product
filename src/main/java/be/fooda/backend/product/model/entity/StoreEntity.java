package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

// LOMBOK
@Table(name = "StoreEntity")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)

// JPA
@Entity

public class StoreEntity {

    @Column(nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;


    @FullTextField
    @Length(min = 2)
    String name;

    @KeywordField
    UUID storeId;

    @JoinColumn
    @OneToOne(cascade = {CascadeType.ALL})
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
