package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

// LOMBOK
@Table(name = "TagEntity")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)

// JPA
@Entity

public class TagEntity {

    @Column(nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;


    @FullTextField
    String value;

    @JoinColumn
    @ManyToOne(cascade = {CascadeType.PERSIST})
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagEntity)) return false;
        final var tagEntity = (TagEntity) o;
        return Objects.equals(getId(), tagEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
