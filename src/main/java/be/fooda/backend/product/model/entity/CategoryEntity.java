package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import javax.persistence.*;
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

public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID categoryId;

    @FullTextField
    @Column(nullable = false, unique = false)
    String title;

    @Lob
    Byte[] icon;

    @ManyToOne(fetch = FetchType.LAZY)
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryEntity)) {
            return false;
        }
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(getCategoryId(), that.getCategoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryId());
    }

    @Override
    public String toString() {
        return "{\"CategoryEntity\":{"
                + "                        \"categoryId\":" + categoryId
                + ",                         \"title\":\"" + title + "\""
                + ",                         \"product\":" + product.getProductId()
                + "}}";
    }
}
