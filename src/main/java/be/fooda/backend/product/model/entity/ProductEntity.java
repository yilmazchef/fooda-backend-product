package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

// LOMBOK
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)

// JPA
@Entity

// HIBERNATE SEARCH
@Indexed

public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @KeywordField
    UUID productId;

    Boolean isActive = Boolean.TRUE;

    @FullTextField
    String name;

    @KeywordField
    String eTrackingId;

    @Lob
    @FullTextField
    String description;

    @GenericField
    Integer limitPerOrder = 0;

    Boolean isFeatured = Boolean.FALSE;

    @IndexedEmbedded
    @OneToOne(mappedBy = "product")
    StoreEntity store;

    public void setStore(StoreEntity store) {
        store.setProduct(this);
        this.store = store;
    }

    @FullTextField
    @Enumerated(EnumType.STRING)
    TypeEntity type;

    @IndexedEmbedded
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<PriceEntity> prices = new LinkedHashSet<>();

    public void setPrices(Set<PriceEntity> prices) {
        this.prices = prices
                .stream()
                .map(this::setOnePrice)
                .collect(Collectors.toSet());
    }

    public void addPrice(PriceEntity price) {
        price.setProduct(this);
        this.prices.add(price);
    }

    public void removePrice(PriceEntity price) {
        price.setProduct(this);
        this.prices.remove(price);
    }

    PriceEntity setOnePrice(PriceEntity price) {
        price.setProduct(this);
        return price;
    }

    @IndexedEmbedded
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<TaxEntity> taxes = new LinkedHashSet<>();

    public void addTax(TaxEntity tax) {
        tax.setProduct(this);
        this.taxes.add(tax);
    }

    public void removeTax(TaxEntity tax) {
        tax.setProduct(this);
        this.taxes.remove(tax);
    }

    public void setTaxes(Set<TaxEntity> taxes) {
        this.taxes = taxes.stream()
                .map(this::setOneTax)
                .collect(Collectors.toSet());
    }

    TaxEntity setOneTax(TaxEntity tax) {
        tax.setProduct(this);
        return tax;
    }

    @OneToOne(mappedBy = "product")
    @IndexedEmbedded
    MediaEntity defaultImage;

    public void setDefaultImage(MediaEntity defaultImage) {
        defaultImage.setProduct(this);
        this.defaultImage = defaultImage;
    }

    @IndexedEmbedded
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<CategoryEntity> categories = new LinkedHashSet<>();

    public void addCategory(CategoryEntity category) {
        category.setProduct(this);
        this.categories.add(category);
    }

    public void removeCategory(CategoryEntity category) {
        category.setProduct(this);
        this.categories.remove(category);
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories
                .stream()
                .map(this::setOneCategory)
                .collect(Collectors.toSet());
    }

    CategoryEntity setOneCategory(CategoryEntity category) {
        category.setProduct(this);
        return category;
    }

    @IndexedEmbedded
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<TagEntity> tags = new LinkedHashSet<>();

    public void addTag(TagEntity tag) {
        tag.setProduct(this);
        this.tags.add(tag);
    }

    public void removeTag(TagEntity tag) {
        tag.setProduct(this);
        this.tags.remove(tag);
    }

    public void setTags(Set<TagEntity> tags) {
        this.tags = tags
                .stream()
                .map(this::setOneTag)
                .collect(Collectors.toSet());

    }

    TagEntity setOneTag(TagEntity tag) {
        tag.setProduct(this);
        return tag;
    }

    @IndexedEmbedded
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<IngredientEntity> ingredients = new LinkedHashSet<>();

    public void addIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        this.ingredients.add(ingredient);
    }

    public void setIngredients(Set<IngredientEntity> ingredients) {
        this.ingredients = ingredients.stream()
                .map(this::setOneIngredient)
                .collect(Collectors.toSet());
    }

    IngredientEntity setOneIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        return ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductEntity)) {
            return false;
        }
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId());
    }

    @Override
    public String toString() {
        return "{\"ProductEntity\":{"
                + "                        \"productId\":" + productId
                + ",                         \"isActive\":\"" + isActive + "\""
                + ",                         \"name\":\"" + name + "\""
                + ",                         \"eTrackingId\":\"" + eTrackingId + "\""
                + ",                         \"description\":\"" + description + "\""
                + ",                         \"limitPerOrder\":\"" + limitPerOrder + "\""
                + ",                         \"isFeatured\":\"" + isFeatured + "\""
                + ",                         \"store\":" + store
                + ",                         \"type\":\"" + type + "\""
                + ",                         \"prices\":" + prices
                + ",                         \"taxes\":" + taxes
                + ",                         \"defaultImage\":" + defaultImage
                + ",                         \"categories\":" + categories
                + ",                         \"tags\":" + tags
                + ",                         \"ingredients\":" + ingredients
                + "}}";
    }
}

