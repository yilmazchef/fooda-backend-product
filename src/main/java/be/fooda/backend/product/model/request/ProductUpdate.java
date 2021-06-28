package be.fooda.backend.product.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdate {

    String name;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    StoreUpdate store;
    TypeUpdate type;
    List<PriceUpdate> prices = new ArrayList<>();
    List<TaxUpdate> taxes = new ArrayList<>();
    MediaUpdate defaultImage;
    List<CategoryUpdate> categories = new ArrayList<>();
    List<TagUpdate> tags = new ArrayList<>();
    List<IngredientUpdate> ingredients = new ArrayList<>();

}

