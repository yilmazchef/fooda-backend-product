package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePriceRequest {

    String title;
    BigDecimal amount;
    LocalTime expiryTime;
    LocalDate expiryDate;
    Boolean isDefault;
    String currency;

}
