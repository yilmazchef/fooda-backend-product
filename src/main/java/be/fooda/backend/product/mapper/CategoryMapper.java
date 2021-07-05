package be.fooda.backend.product.mapper;

import be.fooda.backend.product.model.entity.CategoryEntity;
import be.fooda.backend.product.model.dto.CreateCategoryRequest;
import be.fooda.backend.product.model.dto.UpdateCategoryRequest;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CategoryMapper {

    CategoryEntity toEntity(CreateCategoryRequest source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoryEntity toEntity(UpdateCategoryRequest source, @MappingTarget CategoryEntity target);

    CreateCategoryRequest toCreate(CategoryEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UpdateCategoryRequest toUpdate(CategoryEntity source, @MappingTarget UpdateCategoryRequest target);

}
