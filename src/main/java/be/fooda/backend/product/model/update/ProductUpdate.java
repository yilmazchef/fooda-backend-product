package be.fooda.backend.product.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductUpdate {

    private String name;
    
    private String eTrackingId;

    private String description;

    private Integer limitPerOrder;

    private Boolean isFeatured;

    private StoreUpdate store;

    private TypeUpdate type;

    private List<PriceUpdate> prices;

    private List<TaxUpdate> taxes;

    private ImageUpdate defaultImage;

    private List<CategoryUpdate> categories;

    private List<TagUpdate> tags;

    private List<IngredientUpdate> ingredients;

}

