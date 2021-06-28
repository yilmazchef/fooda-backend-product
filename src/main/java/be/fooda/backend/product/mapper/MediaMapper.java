package be.fooda.backend.product.mapper;

import be.fooda.backend.product.model.entity.MediaEntity;
import be.fooda.backend.product.model.request.MediaCreate;
import be.fooda.backend.product.model.request.MediaUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface MediaMapper {

    MediaEntity toEntity(MediaCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MediaEntity toEntity(MediaUpdate source, @MappingTarget MediaEntity target);

    MediaCreate toCreate(MediaEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MediaUpdate toUpdate(MediaEntity source, @MappingTarget MediaUpdate target);

}
