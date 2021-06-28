package be.fooda.backend.product.mapper;


import be.fooda.backend.product.model.request.ProductCreate;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.model.request.ProductUpdate;
import be.fooda.backend.product.model.response.ProductResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {

    ProductEntity toEntity(ProductCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductEntity toEntity(ProductUpdate source, @MappingTarget ProductEntity target);

    ProductCreate toRequest(ProductEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductUpdate toRequest(ProductEntity source, @MappingTarget ProductUpdate target);

    ProductResponse toResponse(ProductEntity source);

}
