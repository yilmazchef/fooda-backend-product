package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

// LOMBOK
@Table(name = "CategoryEntity")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)

// JPA
@Entity

public class CategoryEntity {

    @Column(nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @FullTextField
    String title;

    @Lob
    Byte[] icon;

    @JoinColumn
    @ManyToOne(cascade = {CascadeType.PERSIST})
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryEntity)) return false;
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
