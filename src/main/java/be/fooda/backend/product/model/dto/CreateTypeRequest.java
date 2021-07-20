package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum CreateTypeRequest {

    SIMPLE("Simple: A product which have no variations"),
    GROUPED("Grouped: A product which has variations"),
    COMPLEX("Complex: multiple products in a product");

    String value;

}
