package be.fooda.backend.product.mapper;


import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.dto.ProductResponse;
import be.fooda.backend.product.model.dto.UpdateProductRequest;
import be.fooda.backend.product.model.entity.ProductEntity;
import org.mapstruct.*;

import java.util.Set;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {

    ProductEntity toEntity(CreateProductRequest source);

    Set<ProductEntity> toEntities(Set<CreateProductRequest> sourceSet);

    ProductEntity toEntity(UpdateProductRequest source, @MappingTarget ProductEntity target);

    CreateProductRequest toRequest(ProductEntity source);

    UpdateProductRequest toRequest(ProductEntity source, @MappingTarget UpdateProductRequest target);

    ProductResponse toResponse(ProductEntity source);

    Set<ProductResponse> toResponses(Set<ProductEntity> sourceSet);

}
