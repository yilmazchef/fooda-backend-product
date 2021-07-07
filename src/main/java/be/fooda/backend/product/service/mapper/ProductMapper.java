package be.fooda.backend.product.service.mapper;


import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.dto.ProductResponse;
import be.fooda.backend.product.model.dto.UpdateProductRequest;
import be.fooda.backend.product.model.entity.ProductEntity;
import org.mapstruct.*;

import java.util.Set;
import java.util.List;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {

    ProductEntity toEntity(CreateProductRequest source);

    Set<ProductEntity> toEntities(Set<CreateProductRequest> sourceSet);

    List<ProductEntity> toEntities(List<CreateProductRequest> sourceList);

    ProductEntity toEntity(UpdateProductRequest source, @MappingTarget ProductEntity target);

    CreateProductRequest toRequest(ProductEntity source);

    UpdateProductRequest toRequest(ProductEntity source, @MappingTarget UpdateProductRequest target);

    ProductResponse toResponse(ProductEntity source);

    Set<ProductResponse> toResponses(Set<ProductEntity> sourceSet);

    List<ProductResponse> toResponses(List<ProductEntity> sourceList);

}
