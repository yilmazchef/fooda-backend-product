package be.fooda.backend.product.mapper;

import be.fooda.backend.product.model.request.TagCreate;
import be.fooda.backend.product.model.entity.TagEntity;
import be.fooda.backend.product.model.request.TagUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TagMapper {

    TagEntity toEntity(TagCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TagEntity toEntity(TagUpdate source, @MappingTarget TagEntity target);

    TagCreate toCreate(TagEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TagUpdate toUpdate(TagEntity source, @MappingTarget TagUpdate target);

}
