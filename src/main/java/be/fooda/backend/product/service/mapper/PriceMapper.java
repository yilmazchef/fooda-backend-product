package be.fooda.backend.product.service.mapper;

import be.fooda.backend.product.model.create.PriceCreate;
import be.fooda.backend.product.model.update.PriceUpdate;
import be.fooda.backend.product.model.entity.PriceEntity;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface PriceMapper {

    PriceEntity toEntity(PriceCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PriceEntity toEntity(PriceUpdate source, @MappingTarget PriceEntity target);

    PriceCreate toCreate(PriceEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PriceUpdate toUpdate(PriceEntity source, @MappingTarget PriceUpdate target);

}
