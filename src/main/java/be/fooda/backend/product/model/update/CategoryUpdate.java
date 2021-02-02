package be.fooda.backend.product.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CategoryUpdate {

    private String title;

    private Byte[] icon;

}
