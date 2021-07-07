package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.Objects;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductRequest {

    // UNIQUE: name + eStoreId

    String name;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    CreateStoreRequest store;
    CreateTypeRequest type;
    List<CreatePriceRequest> prices;
    List<CreateTaxRequest> taxes;
    CreateMediaRequest defaultImage;
    List<CreateCategoryRequest> categories;
    List<CreateTagRequest> tags;
    List<CreateIngredientRequest> ingredients;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateProductRequest)) return false;
        CreateProductRequest that = (CreateProductRequest) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getStore(), that.getStore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStore());
    }
}

