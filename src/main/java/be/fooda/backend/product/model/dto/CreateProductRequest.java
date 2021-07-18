package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    Set<CreatePriceRequest> prices = new LinkedHashSet<>();

    public void addPrice(CreatePriceRequest price) {
        this.prices.add(price);
    }

    public void removePrice(CreatePriceRequest price) {
        this.prices.remove(price);
    }

    List<CreateTaxRequest> taxes = new LinkedHashSet<>();

    public void addTax(CreateTaxRequest tax) {
        tax.setProduct(this);
        this.taxes.add(tax);
    }

    public void removeTax(CreateTaxRequest tax) {
        tax.setProduct(this);
        this.taxes.remove(tax);
    }

    CreateMediaRequest defaultImage;

    public void setDefaultImage(CreateMediaRequest defaultImage) {
        this.defaultImage = defaultImage;
    }

    List<CreateCategoryRequest> categories = new LinkedHashSet<>();

    public void addCategory(CreateCategoryRequest category) {
        this.categories.add(category);
    }

    public void removeCategory(CreateCategoryRequest category) {
        this.categories.remove(category);
    }

    List<CreateTagRequest> tags = new LinkedHashSet<>();

    public void addTag(CreateTagRequest tag) {
        tag.setProduct(this);
        this.tags.add(tag);
    }

    public void removeTag(CreateTagRequest tag) {
        tag.setProduct(this);
        this.tags.remove(tag);
    }

    List<CreateIngredientRequest> ingredients = new LinkedHashSet<>();

    public void addIngredient(CreateIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(CreateIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

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

