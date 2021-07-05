package be.fooda.backend.product.service;

import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.mapper.ProductMapper;
import be.fooda.backend.product.model.dto.*;
import be.fooda.backend.product.model.entity.ProductEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void createSingleProduct(CreateProductRequest request) {
        ProductEntity entity = productMapper.toEntity(request);

        if (request.getIsFeatured().equals(Boolean.TRUE)) {
            request.getTags().add(new CreateTagRequest("featured"));
        }

        ProductEntity savedEntity = productRepository.save(entity);

        log.info("CREATE_SINGLE_PRODUCT_START");
        ProductResponse response = productMapper.toResponse(savedEntity);
        log.info(objectMapper.writeValueAsString(response));
        log.info("CREATE_SINGLE_PRODUCT_END");
    }

    @Override
    public void createMultipleProducts(Set<CreateProductRequest> requestSet) {

        Set<ProductEntity> entitySet = productMapper.toEntities(requestSet);

        for (CreateProductRequest request : requestSet) {
            if (request.getIsFeatured().equals(Boolean.TRUE)) {
                request.getTags().add(new CreateTagRequest("featured"));
            }
        }

        log.info("CREATE_MULTIPLE_PRODUCTS_START");
        Set<ProductEntity> savedProducts = productRepository.saveAll(entitySet).stream().collect(Collectors.toUnmodifiableSet());
        Set<ProductResponse> responseSet = productMapper.toResponses(savedProducts);
        log.info("CREATE_MULTIPLE_PRODUCTS_END");
    }

    @Override
    public void updateSingleProductById(UUID id, UpdateProductRequest request) {

    }

    @Override
    public void updateMultipleProductBySearching(Example<SearchProductRequest> search, UpdateProductRequest request) {

    }

    @Override
    public void deleteSingleProductById(UUID id) {

    }

    @Override
    public void deleteMultipleProductsBySearching(SearchProductRequest search) {

    }

    @Override
    public Optional<ProductResponse> getSingleProductById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Page<ProductResponse> getMultipleProductsByAsPages(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Page<ProductResponse> getMultipleProductsBySearchingAsPages(SearchProductRequest search, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Page<ProductResponse> getMultipleProductsBySearchingAsPages(Map<String, Object> params, int pageNo, int pageSize) {
        return null;
    }

}
