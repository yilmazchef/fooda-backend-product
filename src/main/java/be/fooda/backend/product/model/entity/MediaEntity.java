package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Indexed
public class MediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    UUID id;

    @NotNull
    @Column(columnDefinition = "BINARY(16)")
    UUID eImageId;

    @FullTextField
    @URL
    String url;

    Boolean isDefault;

    @OneToOne
    @ToString.Exclude
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediaEntity)) return false;
        MediaEntity that = (MediaEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
