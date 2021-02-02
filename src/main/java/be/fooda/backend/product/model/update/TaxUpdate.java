package be.fooda.backend.product.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaxUpdate {

    private String title;

    private Double percentage;

    private Boolean isDefault;

}
