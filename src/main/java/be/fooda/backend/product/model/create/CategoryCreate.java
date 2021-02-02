package be.fooda.backend.product.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CategoryCreate {

    private String title;

    private Byte[] icon;

}
