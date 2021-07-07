package be.fooda.backend.product.view.controller;

import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.dto.ProductResponse;
import be.fooda.backend.product.model.http.HttpSuccessMassages;
import be.fooda.backend.product.service.flow.ProductFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product-api")
public class ProductController {

    private static final int PAGE_SIZE_PER_RESULT = 25;
    private static final int DEFAULT_PAGE_NO = 0;

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

    private final ProductFlow productFlow;

    @PostMapping(CREATE_SINGLE_PRODUCT)
    public ResponseEntity<String> createProduct(@RequestBody @Valid CreateProductRequest request) {

        // START_CREATE_FLOW
        productFlow.createProduct(request);

        // RETURN_SUCCESS
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(HttpSuccessMassages.PRODUCT_CREATED.getDescription());
    }

    @GetMapping(GET_ALL_PRODUCTS)
    public ResponseEntity<List<ProductResponse>> findAllProducts(
            @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {

        // START_SELECT_FLOW
        final var responses = productFlow.findAll(pageNo, pageSize);

        // RETURN_ALL_PRODUCTS_IN_PAGES
        return ResponseEntity.status(HttpStatus.FOUND).body(responses);
    }


}
