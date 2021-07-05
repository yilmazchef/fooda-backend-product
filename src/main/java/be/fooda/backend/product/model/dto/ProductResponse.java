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
public class ProductResponse {

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
    StoreResponse store;
    TypeResponse type;
    List<PriceResponse> prices = new ArrayList<>();

    public void setPrices(List<PriceResponse> prices) {
        this.prices = prices
                .stream()
                .map(this::setOnePrice)
                .collect(Collectors.toList());
    }

    public void addPrice(PriceResponse price) {
        if (!this.prices.contains(price)) {
            price.setProduct(this);
            this.prices.add(price);
        }
    }

    public void removePrice(PriceResponse price) {
        price.setProduct(this);
        this.prices.remove(price);
    }

    PriceResponse setOnePrice(PriceResponse price) {
        price.setProduct(this);
        return price;
    }

    List<TaxResponse> taxes = new ArrayList<>();

    public void addTax(TaxResponse tax) {
        tax.setProduct(this);
        this.taxes.add(tax);
    }

    public void removeTax(TaxResponse tax) {
        tax.setProduct(this);
        this.taxes.remove(tax);
    }

    public void setTaxes(List<TaxResponse> taxes) {
        this.taxes = taxes.stream()
                .map(this::setOneTax)
                .collect(Collectors.toList());
    }

    TaxResponse setOneTax(TaxResponse tax) {
        tax.setProduct(this);
        return tax;
    }

    MediaResponse defaultImage;

    public void setDefaultImage(MediaResponse defaultImage) {
        defaultImage.setProduct(this);
        this.defaultImage = defaultImage;
    }

    List<CategoryResponse> categories = new ArrayList<>();

    public void addCategory(CategoryResponse category) {
        category.setProduct(this);
        this.categories.add(category);
    }

    public void removeCategory(CategoryResponse category) {
        category.setProduct(this);
        this.categories.remove(category);
    }

    public void setCategories(List<CategoryResponse> categories) {
        this.categories = categories
                .stream()
                .map(this::setOneCategory)
                .collect(Collectors.toList());
    }

    CategoryResponse setOneCategory(CategoryResponse category) {
        category.setProduct(this);
        return category;
    }

    List<TagResponse> tags = new ArrayList<>();

    public void addTag(TagResponse tag) {
        tag.setProduct(this);
        this.tags.add(tag);
    }

    public void removeTag(TagResponse tag) {
        tag.setProduct(this);
        this.tags.remove(tag);
    }

    public void setTags(List<TagResponse> tags) {
        this.tags = tags
                .stream()
                .map(this::setOneTag)
                .collect(Collectors.toList());

    }

    TagResponse setOneTag(TagResponse tag) {
        tag.setProduct(this);
        return tag;
    }

    List<IngredientResponse> ingredients = new ArrayList<>();

    public void addIngredient(IngredientResponse ingredient) {
        ingredient.setProduct(this);
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(IngredientResponse ingredient) {
        ingredient.setProduct(this);
        this.ingredients.add(ingredient);
    }

    public void setIngredients(List<IngredientResponse> ingredients) {
        this.ingredients = ingredients.stream()
                .map(this::setOneIngredient)
                .collect(Collectors.toList());
    }

    IngredientResponse setOneIngredient(IngredientResponse ingredient) {
        ingredient.setProduct(this);
        return ingredient;
    }

}

