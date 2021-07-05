package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchProductRequest {

    UUID id;
    Boolean isActive;
    String name;
    String eTrackingId;
    String createdBy;
    String lastModifiedBy;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    SearchStoreRequest store;
    SearchTypeRequest type;
    List<SearchPriceRequest> prices = new ArrayList<>();

    public void setPrices(List<SearchPriceRequest> prices) {
        this.prices = prices
                .stream()
                .map(this::setOnePrice)
                .collect(Collectors.toList());
    }

    public void addPrice(SearchPriceRequest price) {
        if (!this.prices.contains(price)) {
            this.prices.add(price);
        }
    }

    public void removePrice(SearchPriceRequest price) {
        this.prices.remove(price);
    }

    SearchPriceRequest setOnePrice(SearchPriceRequest price) {
        return price;
    }

    List<SearchTaxRequest> taxes = new ArrayList<>();

    public void addTax(SearchTaxRequest tax) {
        tax.setProduct(this);
        this.taxes.add(tax);
    }

    public void removeTax(SearchTaxRequest tax) {
        tax.setProduct(this);
        this.taxes.remove(tax);
    }

    public void setTaxes(List<SearchTaxRequest> taxes) {
        this.taxes = taxes.stream()
                .map(this::setOneTax)
                .collect(Collectors.toList());
    }

    SearchTaxRequest setOneTax(SearchTaxRequest tax) {
        tax.setProduct(this);
        return tax;
    }

    SearchMediaRequest defaultImage;

    public void setDefaultImage(SearchMediaRequest defaultImage) {
        this.defaultImage = defaultImage;
    }

    List<SearchCategoryRequest> categories = new ArrayList<>();

    public void addCategory(SearchCategoryRequest category) {
        this.categories.add(category);
    }

    public void removeCategory(SearchCategoryRequest category) {
        this.categories.remove(category);
    }

    public void setCategories(List<SearchCategoryRequest> categories) {
        this.categories = categories
                .stream()
                .map(this::setOneCategory)
                .collect(Collectors.toList());
    }

    SearchCategoryRequest setOneCategory(SearchCategoryRequest category) {
        return category;
    }

    List<SearchTagRequest> tags = new ArrayList<>();

    public void addTag(SearchTagRequest tag) {
        tag.setProduct(this);
        this.tags.add(tag);
    }

    public void removeTag(SearchTagRequest tag) {
        tag.setProduct(this);
        this.tags.remove(tag);
    }

    public void setTags(List<SearchTagRequest> tags) {
        this.tags = tags
                .stream()
                .map(this::setOneTag)
                .collect(Collectors.toList());

    }

    SearchTagRequest setOneTag(SearchTagRequest tag) {
        tag.setProduct(this);
        return tag;
    }

    List<SearchIngredientRequest> ingredients = new ArrayList<>();

    public void addIngredient(SearchIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(SearchIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

    public void setIngredients(List<SearchIngredientRequest> ingredients) {
        this.ingredients = ingredients.stream()
                .map(this::setOneIngredient)
                .collect(Collectors.toList());
    }

    SearchIngredientRequest setOneIngredient(SearchIngredientRequest ingredient) {
        return ingredient;
    }

}

