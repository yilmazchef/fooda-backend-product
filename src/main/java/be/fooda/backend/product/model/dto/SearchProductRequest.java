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
public class SearchProductRequest {

    Boolean isActive;
    String name;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    SearchStoreRequest store;
    SearchTypeRequest type;

    Set<SearchPriceRequest> prices = new LinkedHashSet<>();

    public void addPrice(SearchPriceRequest price) {
        this.prices.add(price);
    }

    public void removePrice(SearchPriceRequest price) {
        this.prices.remove(price);
    }

    List<SearchTaxRequest> taxes = new LinkedHashSet<>();

    public void addTax(SearchTaxRequest tax) {
        tax.setProduct(this);
        this.taxes.add(tax);
    }

    public void removeTax(SearchTaxRequest tax) {
        tax.setProduct(this);
        this.taxes.remove(tax);
    }

    SearchMediaRequest defaultImage;

    public void setDefaultImage(SearchMediaRequest defaultImage) {
        this.defaultImage = defaultImage;
    }

    List<SearchCategoryRequest> categories = new LinkedHashSet<>();

    public void addCategory(SearchCategoryRequest category) {
        this.categories.add(category);
    }

    public void removeCategory(SearchCategoryRequest category) {
        this.categories.remove(category);
    }

    List<SearchTagRequest> tags = new LinkedHashSet<>();

    public void addTag(SearchTagRequest tag) {
        tag.setProduct(this);
        this.tags.add(tag);
    }

    public void removeTag(SearchTagRequest tag) {
        tag.setProduct(this);
        this.tags.remove(tag);
    }

    List<SearchIngredientRequest> ingredients = new LinkedHashSet<>();

    public void addIngredient(SearchIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(SearchIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

}

