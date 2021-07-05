package be.fooda.backend.product.mapper;

import be.fooda.backend.product.model.dto.CreateTaxRequest;
import be.fooda.backend.product.model.dto.UpdateTaxRequest;
import be.fooda.backend.product.model.entity.TaxEntity;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TaxMapper {

    TaxEntity toEntity(CreateTaxRequest source);

    TaxEntity toEntity(UpdateTaxRequest source, @MappingTarget TaxEntity target);

    CreateTaxRequest toCreate(TaxEntity source);

    UpdateTaxRequest toUpdate(TaxEntity source, @MappingTarget UpdateTaxRequest target);

}
