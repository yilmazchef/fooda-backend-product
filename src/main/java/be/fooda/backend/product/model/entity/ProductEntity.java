package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Indexed
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    UUID id;

    Boolean isActive = Boolean.TRUE;

    @FullTextField
    String name;

    @KeywordField
    String eTrackingId;

    @CreatedBy
    String createdBy;

    @LastModifiedBy
    String lastModifiedBy;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Lob
    @FullTextField
    String description;

    @FullTextField
    @GenericField
    Integer limitPerOrder;

    Boolean isFeatured;

    @IndexedEmbedded
    @OneToOne
    StoreEntity store;

    public void setStore(StoreEntity store) {
        store.setProduct(this);
        this.store = store;
    }

    @FullTextField
    @IndexedEmbedded
    @Enumerated(value = EnumType.STRING)
    TypeEntity type;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    @IndexedEmbedded
    List<PriceEntity> prices = new ArrayList<>();

    public void setPrices(List<PriceEntity> prices) {
        this.prices = prices
                .stream()
                .map(this::setOnePrice)
                .collect(Collectors.toList());
    }

    public void addPrice(PriceEntity price) {
        if (!this.prices.contains(price)) {
            price.setProduct(this);
            this.prices.add(price);
        }
    }

    public void removePrice(PriceEntity price) {
        price.setProduct(this);
        this.prices.remove(price);
    }

    PriceEntity setOnePrice(PriceEntity price) {
        price.setProduct(this);
        return price;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @IndexedEmbedded
    @ToString.Exclude
    List<TaxEntity> taxes = new ArrayList<>();

    public void addTax(TaxEntity tax) {
        tax.setProduct(this);
        this.taxes.add(tax);
    }

    public void removeTax(TaxEntity tax) {
        tax.setProduct(this);
        this.taxes.remove(tax);
    }

    public void setTaxes(List<TaxEntity> taxes) {
        this.taxes = taxes.stream()
                .map(this::setOneTax)
                .collect(Collectors.toList());
    }

    TaxEntity setOneTax(TaxEntity tax) {
        tax.setProduct(this);
        return tax;
    }

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @IndexedEmbedded
    MediaEntity defaultImage;

    public void setDefaultImage(MediaEntity defaultImage) {
        defaultImage.setProduct(this);
        this.defaultImage = defaultImage;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    @IndexedEmbedded
    List<CategoryEntity> categories = new ArrayList<>();

    public void addCategory(CategoryEntity category) {
        category.setProduct(this);
        this.categories.add(category);
    }

    public void removeCategory(CategoryEntity category) {
        category.setProduct(this);
        this.categories.remove(category);
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories
                .stream()
                .map(this::setOneCategory)
                .collect(Collectors.toList());
    }

    CategoryEntity setOneCategory(CategoryEntity category) {
        category.setProduct(this);
        return category;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    @IndexedEmbedded
    List<TagEntity> tags = new ArrayList<>();

    public void addTag(TagEntity tag) {
        tag.setProduct(this);
        this.tags.add(tag);
    }

    public void removeTag(TagEntity tag) {
        tag.setProduct(this);
        this.tags.remove(tag);
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags
                .stream()
                .map(this::setOneTag)
                .collect(Collectors.toList());

    }

    TagEntity setOneTag(TagEntity tag) {
        tag.setProduct(this);
        return tag;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    @IndexedEmbedded
    List<IngredientEntity> ingredients = new ArrayList<>();

    public void addIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        this.ingredients.add(ingredient);
    }

    public void setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients.stream()
                .map(this::setOneIngredient)
                .collect(Collectors.toList());
    }

    IngredientEntity setOneIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        return ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity)) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

