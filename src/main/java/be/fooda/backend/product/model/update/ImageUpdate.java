package be.fooda.backend.product.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ImageUpdate {

    private UUID eImageId;

    private String url;

    private Boolean isDefault;

}
