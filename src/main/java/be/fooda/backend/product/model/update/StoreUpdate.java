package be.fooda.backend.product.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class StoreUpdate {

    private String name;

    private UUID eStoreId;

}
