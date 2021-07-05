package be.fooda.backend.product.service;

import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.mapper.ProductMapper;
import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.dto.CreateTagRequest;
import be.fooda.backend.product.model.dto.ProductResponse;
import be.fooda.backend.product.model.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse createSingleProduct(CreateProductRequest request) {
        ProductEntity entity = productMapper.toEntity(request);

        if (request.getIsFeatured().equals(Boolean.TRUE)) {
            request.getTags().add(new CreateTagRequest("featured"));
        }

        ProductEntity savedEntity = productRepository.save(entity);
        ProductResponse response = productMapper.toResponse(savedEntity);
        return response;
    }

}
