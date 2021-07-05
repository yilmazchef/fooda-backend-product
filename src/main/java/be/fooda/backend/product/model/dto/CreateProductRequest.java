package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductRequest {

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

}

