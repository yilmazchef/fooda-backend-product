package be.fooda.backend.product.view.controller;

import be.fooda.backend.product.dao.ProductIndexer;
import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.model.entity.TypeEntity;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.model.http.HttpSuccessMassages;
import be.fooda.backend.product.service.mapper.ProductMapper;
import be.fooda.backend.product.view.client.MediaClient;
import be.fooda.backend.product.view.client.StoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private final ProductIndexer productIndexer;

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
        Page<ProductEntity> foundProducts = productIndexer.search(PageRequest.of(pageNo - 1, pageSize), productName);

        if (foundProducts.hasContent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_PRODUCTS_FOUND);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts.getContent());
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


        if (!storeClient.exist(productBeingUpdated.getStore().getStoreId())) {
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
