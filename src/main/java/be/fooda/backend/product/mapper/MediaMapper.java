package be.fooda.backend.product.mapper;

import be.fooda.backend.product.model.entity.MediaEntity;
import be.fooda.backend.product.model.dto.CreateMediaRequest;
import be.fooda.backend.product.model.dto.UpdateMediaRequest;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface MediaMapper {

    MediaEntity toEntity(CreateMediaRequest source);

    MediaEntity toEntity(UpdateMediaRequest source, @MappingTarget MediaEntity target);

    CreateMediaRequest toCreate(MediaEntity source);

    UpdateMediaRequest toUpdate(MediaEntity source, @MappingTarget UpdateMediaRequest target);

}
