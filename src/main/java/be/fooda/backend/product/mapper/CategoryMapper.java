package be.fooda.backend.product.mapper;

import be.fooda.backend.product.model.entity.CategoryEntity;
import be.fooda.backend.product.model.request.CategoryCreate;
import be.fooda.backend.product.model.request.CategoryUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CategoryMapper {

    CategoryEntity toEntity(CategoryCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoryEntity toEntity(CategoryUpdate source, @MappingTarget CategoryEntity target);

    CategoryCreate toCreate(CategoryEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoryUpdate toUpdate(CategoryEntity source, @MappingTarget CategoryUpdate target);

}
