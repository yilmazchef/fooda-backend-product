package be.fooda.backend.product.model.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCreate {

    private String name;

    private String eTrackingId;

    private String description;

    private Integer limitPerOrder;

    private Boolean isFeatured;

    private StoreCreate store;

    private TypeCreate type;

    private List<PriceCreate> prices;

    private List<TaxCreate> taxes;

    private ImageCreate defaultImage;

    private List<CategoryCreate> categories;

    private List<TagCreate> tags;

    private List<IngredientCreate> ingredients;

}

