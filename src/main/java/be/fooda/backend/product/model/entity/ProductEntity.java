package be.fooda.backend.product.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Indexed
public class ProductEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private Boolean isActive = Boolean.TRUE;

    @Field
    private String name;

    private String eTrackingId;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Lob
    @Field
    private String description;

    @Field
    private Integer limitPerOrder;

    @Field
    private Boolean isFeatured;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private StoreEntity store;

    public void setStore(StoreEntity store) {
        store.setProduct(this);
        this.store = store;
    }

    @Enumerated(value = EnumType.STRING)
    @Field
    private TypeEntity type;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private List<PriceEntity> prices = new ArrayList<>();

    public void setPrices(List<PriceEntity> prices) {
        this.prices = prices
                .stream()
                .map(this::setOnePrice)
                .collect(Collectors.toList());
    }

    public void addPrice(PriceEntity price) {
        price.setProduct(this);
        this.prices.add(price);
    }

    public void removePrice(PriceEntity price) {
        price.setProduct(this);
        this.prices.remove(price);
    }

    private PriceEntity setOnePrice(PriceEntity price) {
        price.setProduct(this);
        return price;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private List<TaxEntity> taxes = new ArrayList<>();

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

    private TaxEntity setOneTax(TaxEntity tax) {
        tax.setProduct(this);
        return tax;
    }

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private ImageEntity defaultImage;

    public void setDefaultImage(ImageEntity defaultImage) {
        defaultImage.setProduct(this);
        this.defaultImage = defaultImage;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private List<CategoryEntity> categories = new ArrayList<>();

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

    private CategoryEntity setOneCategory(CategoryEntity category) {
        category.setProduct(this);
        return category;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private List<TagEntity> tags = new ArrayList<>();

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

    private TagEntity setOneTag(TagEntity tag) {
        tag.setProduct(this);
        return tag;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @IndexedEmbedded
    private List<IngredientEntity> ingredients = new ArrayList<>();

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

    private IngredientEntity setOneIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        return ingredient;
    }

}

