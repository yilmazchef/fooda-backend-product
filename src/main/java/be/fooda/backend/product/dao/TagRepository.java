package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, UUID> {

    @Query("SELECT CASE WHEN COUNT(pt) > 0 THEN true ELSE false END FROM TagResponse pt WHERE pt.value = :value ")
    boolean existByUniqueFields(@Param("value") String value);

    @Query("SELECT pt FROM TagResponse pt WHERE pt.product.id= :productId")
    List<TagEntity> findAllByProductId(@Param("productId") UUID productId);
}
