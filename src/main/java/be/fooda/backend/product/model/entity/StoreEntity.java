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
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)

// JPA
@Entity

public class StoreEntity {

    @Id
    @KeywordField
    UUID storeId;

    @FullTextField
    @Length(min = 2)
    String name;

    @OneToOne
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StoreEntity)) {
            return false;
        }
        StoreEntity that = (StoreEntity) o;
        return Objects.equals(getStoreId(), that.getStoreId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoreId());
    }

    @Override
    public String toString() {
        return "{\"StoreEntity\":{"
                + "                        \"storeId\":" + storeId
                + ",                         \"name\":\"" + name + "\""
                + ",                         \"product\":" + product.getProductId()
                + "}}";
    }
}
