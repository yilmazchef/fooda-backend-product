package be.fooda.backend.product.service.mapper;

import be.fooda.backend.product.model.dto.CreateTagRequest;
import be.fooda.backend.product.model.dto.UpdateTagRequest;
import be.fooda.backend.product.model.entity.TagEntity;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TagMapper {

    TagEntity toEntity(CreateTagRequest source);

    TagEntity toEntity(UpdateTagRequest source, @MappingTarget TagEntity target);

    CreateTagRequest toCreate(TagEntity source);

    UpdateTagRequest toUpdate(TagEntity source, @MappingTarget UpdateTagRequest target);

}
