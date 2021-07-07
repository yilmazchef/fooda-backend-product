package be.fooda.backend.product.service.mapper;

import be.fooda.backend.product.model.entity.CategoryEntity;
import be.fooda.backend.product.model.dto.CreateCategoryRequest;
import be.fooda.backend.product.model.dto.UpdateCategoryRequest;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CategoryMapper {

    CategoryEntity toEntity(CreateCategoryRequest source);

    CategoryEntity toEntity(UpdateCategoryRequest source, @MappingTarget CategoryEntity target);

    CreateCategoryRequest toCreate(CategoryEntity source);

    UpdateCategoryRequest toUpdate(CategoryEntity source, @MappingTarget UpdateCategoryRequest target);

}
