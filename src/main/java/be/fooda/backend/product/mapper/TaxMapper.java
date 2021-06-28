package be.fooda.backend.product.mapper;

import be.fooda.backend.product.model.request.TaxCreate;
import be.fooda.backend.product.model.entity.TaxEntity;
import be.fooda.backend.product.model.request.TaxUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TaxMapper {

    TaxEntity toEntity(TaxCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TaxEntity toEntity(TaxUpdate source, @MappingTarget TaxEntity target);

    TaxCreate toCreate(TaxEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TaxUpdate toUpdate(TaxEntity source, @MappingTarget TaxUpdate target);

}
