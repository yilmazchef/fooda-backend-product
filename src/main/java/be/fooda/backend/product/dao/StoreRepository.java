package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {

}
