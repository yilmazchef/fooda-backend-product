package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.TaxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface TaxRepository extends JpaRepository<TaxEntity, UUID> {


}
