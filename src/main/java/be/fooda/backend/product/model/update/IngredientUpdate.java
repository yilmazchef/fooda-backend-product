package be.fooda.backend.product.model.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientUpdate {

    private String ingredientName;

    private BigDecimal price = BigDecimal.valueOf(0.0);

}
