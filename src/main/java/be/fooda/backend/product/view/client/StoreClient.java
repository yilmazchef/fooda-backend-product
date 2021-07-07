package be.fooda.backend.product.view.client;

import be.fooda.backend.product.model.dto.CreateStoreRequest;
import be.fooda.backend.product.model.dto.UpdateStoreRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class StoreClient {

    //private final RestTemplate restClient;

    //public boolean exists(Long externalStoreId) {


    public boolean exist(final UUID eStoreId) {

        return true;
    }

    public boolean exist(final CreateStoreRequest store) {

        return true;
    }


    public boolean exist(final Set<UUID> storeIdSet) {

        return true;
    }


    public boolean exists(UpdateStoreRequest store) {
        return true;
    }

}
