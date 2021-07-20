package be.fooda.backend.product.view.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class StoreClient {

    // private final RestTemplate restClient;

    public boolean exist(final UUID storeId) {
        return true;
    }

}
