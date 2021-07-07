package be.fooda.backend.product.view.client;

import be.fooda.backend.product.model.dto.CreateMediaRequest;
import be.fooda.backend.product.model.dto.UpdateMediaRequest;
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

    public boolean exist(CreateMediaRequest image) {
        return true;
    }

    public boolean exists(UpdateMediaRequest image) {
        return true;
    }
}
