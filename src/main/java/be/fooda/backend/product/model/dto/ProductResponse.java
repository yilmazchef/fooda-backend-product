package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    UUID productId;
    Boolean isActive;
    String name;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    StoreResponse store;
    TypeResponse type;

    Set<PriceResponse> prices = new LinkedHashSet<>();
    
    public void addPrice(PriceResponse price) {
        this.prices.add(price);
    }

    public void removePrice(PriceResponse price) {
        this.prices.remove(price);
    }

    Set<TaxResponse> taxes = new LinkedHashSet<>();

    public void addTax(TaxResponse tax) {
        this.taxes.add(tax);
    }

    public void removeTax(TaxResponse tax) {
        this.taxes.remove(tax);
    }

    MediaResponse defaultImage;

    Set<CategoryResponse> categories = new LinkedHashSet<>();

    public void addCategory(CategoryResponse category) {
        this.categories.add(category);
    }

    public void removeCategory(CategoryResponse category) {
        this.categories.remove(category);
    }

    Set<TagResponse> tags = new LinkedHashSet<>();

    public void addTag(TagResponse tag) {
        this.tags.add(tag);
    }

    public void removeTag(TagResponse tag) {
        this.tags.remove(tag);
    }

    Set<IngredientResponse> ingredients = new LinkedHashSet<>();

    public void addIngredient(IngredientResponse ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(IngredientResponse ingredient) {
        this.ingredients.add(ingredient);
    }

}

