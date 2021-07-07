package be.fooda.backend.product.view.controller;

import be.fooda.backend.product.view.client.MediaClient;
import be.fooda.backend.product.view.client.StoreClient;
import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.dao.ProductSearch;
import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.model.entity.TypeEntity;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.model.http.HttpSuccessMassages;
import be.fooda.backend.product.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/")
public class ProductController {

    private static final String PAGE_SIZE_PER_RESULT = "25";

    private static final String GET_ALL_PRODUCTS = "get_all_products";
    private static final String SEARCH_BY_PRODUCT_NAME = "search_by_product_name";
    private static final String SEARCH_BY_DESCRIPTION = "search_by_description";
    private static final String SEARCH_BY_INGREDIENTS = "search_by_ingredients";
    private static final String SEARCH_BY_CATEGORIES = "search_by_categories";
    private static final String SEARCH_BY_TAGS = "search_by_tags";
    private static final String FILTER_BY_PRICE_RANGE = "filter_by_price_range";
    private static final String SEARCH_BY_STORE_NAME = "search_by_store_name";
    private static final String FILTER_BY_FEATURED = "filter_by_featured";
    private static final String CREATE_SINGLE_PRODUCT = "create_single_product";
    private static final String CREATE_LIST_OF_PRODUCTS = "create_list_of_products";
    private static final String GET_BY_PRODUCT_ID = "get_by_product_id";
    private static final String COMBINED_SEARCH = "combined_search";
    private static final String EXISTS_BY_PRODUCT_ID = "exists_by_product_id";
    private static final String UPDATE_PRODUCT_INFO = "update_product_info";
    private static final String DELETE_PRODUCT_BY_ID = "delete_product_by_id";

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductSearch indexRepository;

    private final StoreClient storeClient;
    private final MediaClient mediaClient;


    @GetMapping(GET_ALL_PRODUCTS)
    public ResponseEntity getAllProducts(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = PAGE_SIZE_PER_RESULT) int pageSize) {

        Pageable paging = PageRequest.of(pageNo - 1, pageSize);
        List<ProductEntity> pageProducts = productRepository.findAllByIsActive(true, paging);
        if (pageProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_PRODUCTS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(pageProducts);
    }

    @GetMapping(SEARCH_BY_PRODUCT_NAME)
    public ResponseEntity searchProductsByName(@RequestParam String productName, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = PAGE_SIZE_PER_RESULT) int pageSize) {
        List<ProductEntity> foundProducts = indexRepository
                .searchProductsByName(productName, pageNo - 1, pageSize);

        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_PRODUCTS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);
    }

    @GetMapping(SEARCH_BY_DESCRIPTION)
    public ResponseEntity searchProductsByDescription(@RequestParam String productDescription, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = PAGE_SIZE_PER_RESULT) int pageSize) {
        List<ProductEntity> foundProducts = indexRepository.searchByDescription(productDescription, pageNo - 1, pageSize);

        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_PRODUCTS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);
    }

    @GetMapping(SEARCH_BY_INGREDIENTS)
    public ResponseEntity searchProductsByIngredients(@RequestParam Set<String> ingredients, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = PAGE_SIZE_PER_RESULT) int pageSize) {
        List<ProductEntity> foundProducts = indexRepository.searchByIngredients(ingredients, pageNo - 1, pageSize);

        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_PRODUCTS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);

    }

    @GetMapping(SEARCH_BY_CATEGORIES)
    public ResponseEntity searchProductsByCategories(@RequestParam Set<String> categories, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = PAGE_SIZE_PER_RESULT) int pageSize) {
        List<ProductEntity> foundProducts = indexRepository.searchByCategories(categories, pageNo - 1, pageSize);

        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_PRODUCTS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);

    }

    @GetMapping(SEARCH_BY_TAGS)
    public ResponseEntity searchProductsByTags(@RequestParam Set<String> tags, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = PAGE_SIZE_PER_RESULT) int pageSize) {
        List<ProductEntity> foundProducts = indexRepository.searchByTags(tags, pageNo - 1, pageSize);

        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_PRODUCTS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);
    }

    @GetMapping(FILTER_BY_PRICE_RANGE)
    public ResponseEntity searchProductsByPriceRange(@RequestParam @PositiveOrZero BigDecimal minPrice,
                                                     @RequestParam BigDecimal maxPrice, @RequestParam(defaultValue = "1") int pageNo,
                                                     @RequestParam(defaultValue = PAGE_SIZE_PER_RESULT) int pageSize,
                                                     @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        List<ProductEntity> foundProductPrices = indexRepository.searchByPriceRange(minPrice, maxPrice, pageNo - 1, pageSize, isActive);

        if (foundProductPrices.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_PRODUCTS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProductPrices);
    }

    @GetMapping(SEARCH_BY_STORE_NAME)
    public ResponseEntity searchByStore(@RequestParam String storeName, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = PAGE_SIZE_PER_RESULT) int pageSize) {

        final List<ProductEntity> foundProducts = indexRepository.searchByStoreName(storeName, pageNo - 1, pageSize);
        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_STORE_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);
    }

    @GetMapping(FILTER_BY_FEATURED)
    public ResponseEntity filterFeatured(@RequestParam Boolean isFeatured, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = PAGE_SIZE_PER_RESULT) int pageSize) {
        List<ProductEntity> foundProducts = indexRepository.filterFeatured(isFeatured, pageNo - 1, pageSize);

        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_PRODUCTS_IS_FEATURED);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);

    }

    @PostMapping(CREATE_LIST_OF_PRODUCTS)
    public List<ResponseEntity> createListOfProducts(@RequestBody @Valid List<CreateProductRequest> products) {

        return products.stream()
                .map(this::createProduct)
                .collect(Collectors.toList());
    }

    @PostMapping(CREATE_SINGLE_PRODUCT)
    public ResponseEntity createProduct(@RequestBody @Valid CreateProductRequest product) {
        final ProductEntity example = productMapper.toEntity(product);
        productRepository.save(example);
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMassages.PRODUCT_CREATED);
    }

    @GetMapping(GET_BY_PRODUCT_ID)
    public ResponseEntity findById(@RequestParam UUID id, @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final Optional<ProductEntity> foundProduct = productRepository.findById(id);
        if (!foundProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMassages.PRODUCT_NOT_FOUND);
        if (!foundProduct.get().getIsActive().equals(isActive))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMassages.PRODUCT_NOT_FOUND);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(foundProduct.get());
    }

    @GetMapping(EXISTS_BY_PRODUCT_ID)
    public ResponseEntity existsById(@RequestParam @PositiveOrZero UUID id, @RequestParam Boolean isActive) {
        if (productRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMassages.PRODUCT_EXISTS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.PRODUCT_DOES_NOT_EXIST);
        }
    }

    @GetMapping(COMBINED_SEARCH)
    public ResponseEntity search(@RequestParam Set<String> keyword, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = PAGE_SIZE_PER_RESULT) int pageSize,
                                 @RequestParam(defaultValue = "true", required = false) Boolean isActive) {
        final List<ProductEntity> foundProducts = indexRepository.combined(keyword, pageNo - 1, pageSize, isActive);

        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_PRODUCTS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);
    }


    @PutMapping(UPDATE_PRODUCT_INFO)
    public ResponseEntity patchProduct(
            @RequestParam UUID id,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String productDescription,
            @RequestParam(required = false) Integer limitsPerProduct,
            @RequestParam(required = false) Boolean isFeatured,
            @RequestParam(required = false) String type) {

        if (productName == null && productName.isEmpty() &&
                productDescription == null && productDescription.isEmpty() &&
                limitsPerProduct == null && limitsPerProduct < 0 &&
                type == null && type.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_FIELD_IS_SENT_TO_UPDATE_REQUEST);
        }

        final Optional<ProductEntity> foundProduct = productRepository.findById(id);

        if (!foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.PRODUCT_DOES_NOT_EXIST);
        }

        ProductEntity productBeingUpdated = foundProduct.get();


        if (!storeClient.exist(productBeingUpdated.getStore().getEStoreId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMassages.STORE_DOES_NOT_EXIST);
        }

        if (productName != null && !productName.isEmpty())
            productBeingUpdated.setName(productName);
        if (productDescription != null && !productDescription.isEmpty())
            productBeingUpdated.setDescription(productDescription);
        if (limitsPerProduct != null && !(limitsPerProduct < 0))
            productBeingUpdated.setLimitPerOrder(limitsPerProduct);
        if (isFeatured != null)
            productBeingUpdated.setIsFeatured(isFeatured);

        if (type != null && !type.isEmpty())
            productBeingUpdated.setType(TypeEntity.valueOf(type));

        productRepository.save(productBeingUpdated);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMassages.PRODUCT_UPDATED);

    }

    @PatchMapping(DELETE_PRODUCT_BY_ID)
    public ResponseEntity deleteProductById(@RequestParam UUID id) {
        Optional<ProductEntity> foundProduct = productRepository.findById(id);

        if (foundProduct.isPresent()) {
            ProductEntity productBeingDeleted = foundProduct.get();
            productBeingDeleted.setIsActive(Boolean.FALSE);
            productRepository.save(productBeingDeleted);

            if (!productRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(HttpFailureMassages.PRODUCT_COULD_NOT_BE_DELETED);
            } else {

                return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMassages.PRODUCT_DELETED);

            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.PRODUCT_DOES_NOT_EXIST);
        }
    }

}
