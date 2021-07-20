package be.fooda.backend.product.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum SearchTypeRequest {

    SIMPLE("Simple: A product which have no variations"),
    GROUPED("Grouped: A product which has variations"),
    COMPLEX("Complex: multiple products in a product");

    String value;

}
