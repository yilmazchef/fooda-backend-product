package be.fooda.backend.product.model.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxCreate {

    private String title;

    private Double percentage;

    private Boolean isDefault;

}
