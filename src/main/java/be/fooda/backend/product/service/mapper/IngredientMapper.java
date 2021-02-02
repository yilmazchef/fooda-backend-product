package be.fooda.backend.product.service.mapper;

import be.fooda.backend.product.model.create.IngredientCreate;
import be.fooda.backend.product.model.entity.IngredientEntity;
import be.fooda.backend.product.model.update.IngredientUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface IngredientMapper {

    IngredientEntity toEntity(IngredientCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    IngredientEntity toEntity(IngredientUpdate source, @MappingTarget IngredientEntity target);

    IngredientCreate toCreate(IngredientEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    IngredientUpdate toUpdate(IngredientEntity source, @MappingTarget IngredientUpdate target);

}
