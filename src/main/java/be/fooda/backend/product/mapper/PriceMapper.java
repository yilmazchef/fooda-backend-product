package be.fooda.backend.product.mapper;

import be.fooda.backend.product.model.dto.CreatePriceRequest;
import be.fooda.backend.product.model.dto.UpdatePriceRequest;
import be.fooda.backend.product.model.entity.PriceEntity;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PriceMapper {

    PriceEntity toEntity(CreatePriceRequest source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PriceEntity toEntity(UpdatePriceRequest source, @MappingTarget PriceEntity target);

    CreatePriceRequest toCreate(PriceEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UpdatePriceRequest toUpdate(PriceEntity source, @MappingTarget UpdatePriceRequest target);

}
