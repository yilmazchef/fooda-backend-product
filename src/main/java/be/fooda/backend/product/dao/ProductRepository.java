package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findAllByIsActive(boolean isActive, Pageable pageable);

    Optional<ProductEntity> findByNameAndStore_EStoreId(String name, UUID eStoreId);

    boolean existsByNameAndStore_EStoreId(String name, UUID eStoreId);
}
