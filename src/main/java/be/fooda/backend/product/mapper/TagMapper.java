package be.fooda.backend.product.mapper;

import be.fooda.backend.product.model.dto.CreateTagRequest;
import be.fooda.backend.product.model.dto.UpdateTagRequest;
import be.fooda.backend.product.model.entity.TagEntity;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TagMapper {

    TagEntity toEntity(CreateTagRequest source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TagEntity toEntity(UpdateTagRequest source, @MappingTarget TagEntity target);

    CreateTagRequest toCreate(TagEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UpdateTagRequest toUpdate(TagEntity source, @MappingTarget UpdateTagRequest target);

}
