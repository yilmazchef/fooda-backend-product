package be.fooda.backend.product.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ImageCreate {

    private UUID eImageId;

    private String url;

    private Boolean isDefault;

}
