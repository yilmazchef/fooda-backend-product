package be.fooda.backend.product.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import be.fooda.backend.product.model.entity.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, UUID> {

}
