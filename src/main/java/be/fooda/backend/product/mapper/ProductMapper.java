package be.fooda.backend.product.mapper;


import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.dto.UpdateProductRequest;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.model.dto.ProductResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {

    ProductEntity toEntity(CreateProductRequest source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductEntity toEntity(UpdateProductRequest source, @MappingTarget ProductEntity target);

    CreateProductRequest toRequest(ProductEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UpdateProductRequest toRequest(ProductEntity source, @MappingTarget UpdateProductRequest target);

    ProductResponse toResponse(ProductEntity source);

}
