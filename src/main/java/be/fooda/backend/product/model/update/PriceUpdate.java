package be.fooda.backend.product.model.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceUpdate {

    private String title;

    private BigDecimal amount;

    private LocalTime expiryTime;

    private LocalDate expiryDate;

    private Boolean isDefault;

    private String currency;

}
