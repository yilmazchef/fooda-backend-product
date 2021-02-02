package be.fooda.backend.product.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class IngredientCreate {

    private String ingredientName;

    private BigDecimal price = BigDecimal.valueOf(0.0);

}
