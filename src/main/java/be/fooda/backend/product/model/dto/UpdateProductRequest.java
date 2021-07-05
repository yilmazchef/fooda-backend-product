package be.fooda.backend.product.model.dto;

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
public class UpdateProductRequest {

    String name;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    UpdateStoreRequest store;
    UpdateTypeRequest type;
    List<UpdatePriceRequest> prices = new ArrayList<>();
    List<UpdateTaxRequest> taxes = new ArrayList<>();
    UpdateMediaRequest defaultImage;
    List<UpdateCategoryRequest> categories = new ArrayList<>();
    List<UpdateTagRequest> tags = new ArrayList<>();
    List<UpdateIngredientRequest> ingredients = new ArrayList<>();

}

