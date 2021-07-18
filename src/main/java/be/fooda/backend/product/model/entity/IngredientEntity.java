package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

import javax.persistence.*;
import java.math.BigDecimal;
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

public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID ingredientId;

    @FullTextField
    String title;

    @GenericField
    BigDecimal price = BigDecimal.valueOf(0.0);

    @ManyToOne(fetch = FetchType.LAZY)
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IngredientEntity)) {
            return false;
        }
        IngredientEntity that = (IngredientEntity) o;
        return Objects.equals(getIngredientId(), that.getIngredientId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIngredientId());
    }

    @Override
    public String toString() {
        return "{\"IngredientEntity\":{"
                + "                        \"ingredientId\":" + ingredientId
                + ",                         \"title\":\"" + title + "\""
                + ",                         \"price\":\"" + price + "\""
                + ",                         \"product\":" + product.getProductId()
                + "}}";
    }
}
