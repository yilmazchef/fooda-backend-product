package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

// LOMBOK
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)

// JPA
@Entity

public class MediaEntity {

    @Id
    @Column(nullable = false, unique = true)
    UUID mediaId;

    @FullTextField
    @URL
    String url;

    Boolean isDefault = Boolean.FALSE;

    @OneToOne
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MediaEntity)) {
            return false;
        }
        MediaEntity that = (MediaEntity) o;
        return Objects.equals(getMediaId(), that.getMediaId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMediaId());
    }

    @Override
    public String toString() {
        return "{\"MediaEntity\":{"
                + "                        \"mediaId\":" + mediaId
                + ",                         \"url\":\"" + url + "\""
                + ",                         \"isDefault\":\"" + isDefault + "\""
                + ",                         \"product\":" + product.getProductId()
                + "}}";
    }
}
