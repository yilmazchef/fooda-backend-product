package be.fooda.backend.product.client;

import be.fooda.backend.product.model.request.MediaCreate;
import be.fooda.backend.product.model.request.MediaUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class MediaClient {

    //private RestTemplate restClient;

    public boolean exist(UUID eImageId) {
        return true;
    }

    public boolean exist(MediaCreate image) {
        return true;
    }

    public boolean exists(MediaUpdate image) {
        return true;
    }
}
