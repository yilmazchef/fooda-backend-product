package be.fooda.backend.product.service.mapper;

import be.fooda.backend.product.model.create.TagCreate;
import be.fooda.backend.product.model.entity.TagEntity;
import be.fooda.backend.product.model.update.TagUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface TagMapper {

    TagEntity toEntity(TagCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TagEntity toEntity(TagUpdate source, @MappingTarget TagEntity target);

    TagCreate toCreate(TagEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TagUpdate toUpdate(TagEntity source, @MappingTarget TagUpdate target);

}
