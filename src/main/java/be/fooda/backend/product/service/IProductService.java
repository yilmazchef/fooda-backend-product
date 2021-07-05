package be.fooda.backend.product.service;

import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.dto.ProductResponse;
import be.fooda.backend.product.model.dto.SearchProductRequest;
import be.fooda.backend.product.model.dto.UpdateProductRequest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IProductService {

    void createSingleProduct(CreateProductRequest request);

    void createMultipleProducts(Set<CreateProductRequest> requestSet);

    void updateSingleProductById(UUID id, UpdateProductRequest request);

    void updateMultipleProductBySearching(Example<SearchProductRequest> search, UpdateProductRequest request);

    void deleteSingleProductById(UUID id);

    void deleteMultipleProductsBySearching(SearchProductRequest search);

    Optional<ProductResponse> getSingleProductById(UUID id);

    Page<ProductResponse> getMultipleProductsByAsPages(int pageNo, int pageSize);

    Page<ProductResponse> getMultipleProductsBySearchingAsPages(SearchProductRequest search, int pageNo, int pageSize);

    Page<ProductResponse> getMultipleProductsBySearchingAsPages(Map<String, Object> params, int pageNo, int pageSize);
}
