package be.fooda.backend.product.mapper;

import be.fooda.backend.product.model.request.PriceCreate;
import be.fooda.backend.product.model.request.PriceUpdate;
import be.fooda.backend.product.model.entity.PriceEntity;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PriceMapper {

    PriceEntity toEntity(PriceCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PriceEntity toEntity(PriceUpdate source, @MappingTarget PriceEntity target);

    PriceCreate toCreate(PriceEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PriceUpdate toUpdate(PriceEntity source, @MappingTarget PriceUpdate target);

}
