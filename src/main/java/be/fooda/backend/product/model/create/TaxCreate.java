package be.fooda.backend.product.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaxCreate {

    private String title;

    private Double percentage;

    private Boolean isDefault;

}
