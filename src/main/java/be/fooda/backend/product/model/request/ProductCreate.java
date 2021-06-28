package be.fooda.backend.product.model.request;

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
public class ProductCreate {

    String name;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    StoreCreate store;
    TypeCreate type;
    List<PriceCreate> prices;
    List<TaxCreate> taxes;
    MediaCreate defaultImage;
    List<CategoryCreate> categories;
    List<TagCreate> tags;
    List<IngredientCreate> ingredients;

}

