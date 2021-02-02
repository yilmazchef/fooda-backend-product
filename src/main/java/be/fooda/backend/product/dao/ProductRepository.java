package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.ProductEntity;
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

    @Query("SELECT p FROM ProductEntity p WHERE p.name = :productName AND p.isActive = true")
    List<ProductEntity> findByProductName(@Param("productName") final String productName);

    @Query("SELECT p FROM ProductEntity p WHERE p.description = :description AND p.isActive = true")
    List<ProductEntity> searchByDescription(@Param("description") final String description);

    @Query("SELECT p FROM ProductEntity p WHERE p.isFeatured = :isFeatured AND p.isActive = true")
    List<ProductEntity> findByFeatured(@Param("isFeatured") Boolean isFeatured);

    @Query("SELECT p FROM ProductEntity p WHERE p.isActive=:isActive AND p.id IN (SELECT pp.product.id FROM PriceEntity pp WHERE pp.amount >= :minPrice AND pp.amount <= :maxPrice)")
    List<ProductEntity> findByPriceRange(@Param("minPrice") final BigDecimal minPrice, @Param("maxPrice") final BigDecimal maxPrice, Boolean isActive);

    @Query("SELECT p FROM ProductEntity p WHERE p.id IN (SELECT pp.product.id FROM PriceEntity pp WHERE pp.id = :priceId AND pp.expiryDate= :expiryDate)")
    List<ProductEntity> findByPriceIdAndExpiryDate(@Param("priceId") final UUID priceId, @Param("expiryDate") final LocalDate expiryDate);

    @Query("SELECT p FROM ProductEntity p WHERE p.store.eStoreId = :externalStoreId AND p.isActive = true")
    List<ProductEntity> findByExternalStoreId(@Param("externalStoreId") UUID eStoreId);

    @Query("SELECT p FROM ProductEntity p WHERE p.id IN (SELECT pc.product.id FROM CategoryEntity pc WHERE pc.title IN (:categories))")
    List<ProductEntity> findByCategories(@Param("categories") final Set<String> categories);

    @Query("SELECT p FROM ProductEntity p WHERE p.id IN (SELECT pt.product.id FROM TagEntity pt WHERE pt.value IN (:tags))")
    List<ProductEntity> findByTags(@Param("tags") final Set<String> tags);

    @Query("SELECT p FROM ProductEntity p WHERE p.store.name = :storeName")
    Optional<ProductEntity> findByStoreName(@Param("storeName") final String storeName);

    @Query("SELECT p FROM ProductEntity p WHERE p.id IN (SELECT pp.product.id FROM PriceEntity pp WHERE pp.id= :priceId)")
    List<ProductEntity> findByPriceId(@Param("priceId") final UUID priceId);

    @Query("SELECT p FROM ProductEntity p WHERE p.id IN (SELECT pi.product.id FROM IngredientEntity pi WHERE pi.ingredientName IN (:ingredients))")
    List<ProductEntity> findByIngredients(@Param("ingredients") Set<String> ingredients);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM ProductEntity p WHERE p.name = :productName AND p.store.eStoreId = :eStoreId")
    boolean existByUniqueFields(@Param("productName") final String productName, @Param("eStoreId") final UUID eStoreId);

    List<ProductEntity> findAllByIsActive(boolean isActive, Pageable pageable);

    Optional<ProductEntity> findByIdAndIsActive(UUID id, boolean isActive);


}
