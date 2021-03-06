package be.fooda.backend.product.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import be.fooda.backend.product.model.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findAllByIsActive(Boolean isActive, Pageable pageable);

    List<ProductEntity> findAllByTitleContains(String title);

    Optional<ProductEntity> findByTitleAndStore_StoreId(String name, UUID storeId);

    boolean existsByTitleAndStore_StoreId(String name, UUID storeId);
}
