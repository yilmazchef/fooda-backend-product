package be.fooda.backend.product.mapper;

import be.fooda.backend.product.model.dto.CreateIngredientRequest;
import be.fooda.backend.product.model.dto.UpdateIngredientRequest;
import be.fooda.backend.product.model.entity.IngredientEntity;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IngredientMapper {

    IngredientEntity toEntity(CreateIngredientRequest source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    IngredientEntity toEntity(UpdateIngredientRequest source, @MappingTarget IngredientEntity target);

    CreateIngredientRequest toCreate(IngredientEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UpdateIngredientRequest toUpdate(IngredientEntity source, @MappingTarget UpdateIngredientRequest target);

}
