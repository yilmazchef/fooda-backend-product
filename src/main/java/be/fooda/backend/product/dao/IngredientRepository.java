package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientRepository extends JpaRepository<IngredientEntity, UUID> {

    @Query("SELECT pi FROM IngredientEntity pi WHERE pi.product.id= :id")
    List<IngredientEntity> findAllByProductId(@Param("id") UUID id);

    @Query("SELECT CASE WHEN COUNT(pi) > 0 THEN true ELSE false END FROM IngredientEntity pi WHERE pi.ingredientName = :ingredientName AND pi.price = :price")
    boolean existByUniqueFields(@Param("ingredientName") String ingredientName, @Param("price") BigDecimal price);

    @Query("SELECT pi FROM IngredientEntity pi WHERE pi.product.id= :productId")
    Optional<IngredientEntity> findByProductId(@Param("productId") UUID productId);
}
