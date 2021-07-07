package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

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
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    UUID id;

    @FullTextField
    String value;

    @ManyToOne
    @ToString.Exclude
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagEntity)) return false;
        TagEntity tagEntity = (TagEntity) o;
        return Objects.equals(getId(), tagEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
