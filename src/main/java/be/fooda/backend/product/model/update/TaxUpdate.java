package be.fooda.backend.product.model.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxUpdate {

    private String title;

    private Double percentage;

    private Boolean isDefault;

}
