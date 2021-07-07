package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MediaRepository extends JpaRepository<MediaEntity, UUID> {


}
