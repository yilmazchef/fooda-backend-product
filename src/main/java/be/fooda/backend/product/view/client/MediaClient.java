package be.fooda.backend.product.view.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MediaClient {

    //private RestTemplate restClient;

    public boolean exist(UUID eImageId) {
        return true;
    }
}
