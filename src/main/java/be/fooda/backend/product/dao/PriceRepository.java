package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, UUID> {


    @Query("SELECT pp FROM PriceResponse pp WHERE pp.product.id= :productId")
    List<PriceEntity> findAllByProductId(@Param("productId") UUID productId);

    @Query("SELECT pp FROM PriceResponse pp WHERE pp.product.id= :productId")
    Optional<PriceEntity> findByProductId(@Param("productId") UUID productId);
}
