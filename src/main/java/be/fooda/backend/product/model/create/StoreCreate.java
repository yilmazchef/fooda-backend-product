package be.fooda.backend.product.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class StoreCreate {

    private String name;

    private UUID eStoreId;

}
