package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaRepository extends JpaRepository<ImageEntity, UUID> {


}
