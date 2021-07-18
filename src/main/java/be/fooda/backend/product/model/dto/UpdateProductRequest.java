package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    
    Set<UpdatePriceRequest> prices = new LinkedHashSet<>();

    public void addPrice(UpdatePriceRequest price) {
        this.prices.add(price);
    }

    public void removePrice(UpdatePriceRequest price) {
        this.prices.remove(price);
    }

    List<UpdateTaxRequest> taxes = new LinkedHashSet<>();

    public void addTax(UpdateTaxRequest tax) {
        tax.setProduct(this);
        this.taxes.add(tax);
    }

    public void removeTax(UpdateTaxRequest tax) {
        tax.setProduct(this);
        this.taxes.remove(tax);
    }

    UpdateMediaRequest defaultImage;

    public void setDefaultImage(UpdateMediaRequest defaultImage) {
        this.defaultImage = defaultImage;
    }

    List<UpdateCategoryRequest> categories = new LinkedHashSet<>();

    public void addCategory(UpdateCategoryRequest category) {
        this.categories.add(category);
    }

    public void removeCategory(UpdateCategoryRequest category) {
        this.categories.remove(category);
    }

    List<UpdateTagRequest> tags = new LinkedHashSet<>();

    public void addTag(UpdateTagRequest tag) {
        tag.setProduct(this);
        this.tags.add(tag);
    }

    public void removeTag(UpdateTagRequest tag) {
        tag.setProduct(this);
        this.tags.remove(tag);
    }

    List<UpdateIngredientRequest> ingredients = new LinkedHashSet<>();

    public void addIngredient(UpdateIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(UpdateIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

}

