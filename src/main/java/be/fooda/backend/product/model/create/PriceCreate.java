package be.fooda.backend.product.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class PriceCreate {

    private String title;

    private BigDecimal amount;

    private LocalTime expiryTime;

    private LocalDate expiryDate;

    private Boolean isDefault;

    private String currency;

}
