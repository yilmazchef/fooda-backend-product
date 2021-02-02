package be.fooda.backend.product.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class IngredientUpdate {

    private String ingredientName;

    private BigDecimal price = BigDecimal.valueOf(0.0);

}
