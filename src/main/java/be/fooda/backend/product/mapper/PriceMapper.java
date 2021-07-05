package be.fooda.backend.product.mapper;

import be.fooda.backend.product.model.dto.CreatePriceRequest;
import be.fooda.backend.product.model.dto.UpdatePriceRequest;
import be.fooda.backend.product.model.entity.PriceEntity;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PriceMapper {

    PriceEntity toEntity(CreatePriceRequest source);

    PriceEntity toEntity(UpdatePriceRequest source, @MappingTarget PriceEntity target);

    CreatePriceRequest toCreate(PriceEntity source);

    UpdatePriceRequest toUpdate(PriceEntity source, @MappingTarget UpdatePriceRequest target);

}
