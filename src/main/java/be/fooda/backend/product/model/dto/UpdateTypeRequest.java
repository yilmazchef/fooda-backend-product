package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum UpdateTypeRequest {

    SIMPLE("A product which have no variations"),
    GROUPED("A product which has variations"),
    COMPLEX("multiple products in a product");

    String value;

}
