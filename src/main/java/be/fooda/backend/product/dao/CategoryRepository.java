package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    @Query("SELECT pc FROM CategoryEntity pc WHERE pc.product.id= :id")
    List<CategoryEntity> findAllByProductId(@Param("id") UUID id);

    @Query("SELECT pc FROM CategoryEntity pc WHERE pc.product.id= :productId")
    Optional<CategoryEntity> findByProductId(@Param("productId") UUID productId);

}
