package be.fooda.backend.product.service.mapper;

import be.fooda.backend.product.model.create.TaxCreate;
import be.fooda.backend.product.model.entity.TaxEntity;
import be.fooda.backend.product.model.update.TaxUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface TaxMapper {

    TaxEntity toEntity(TaxCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TaxEntity toEntity(TaxUpdate source, @MappingTarget TaxEntity target);

    TaxCreate toCreate(TaxEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TaxUpdate toUpdate(TaxEntity source, @MappingTarget TaxUpdate target);

}
