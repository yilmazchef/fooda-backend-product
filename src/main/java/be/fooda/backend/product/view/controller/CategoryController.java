package be.fooda.backend.product.view.controller;

import be.fooda.backend.product.dao.CategoryRepository;
import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.model.entity.CategoryEntity;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.model.http.HttpSuccessMassages;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/category")
public class CategoryController {


    private static final String PAGE_SIZE_PER_RESULT = "25";
    private static final String GET_ALL_CATEGORIES = "get_all_categories";
    private static final String GET_ALL_CATEGORIES_BY_PRODUCT_ID = "get_all_categories_by_product_id";
    private static final String GET_CATEGORY_BY_ID = "get_all_category_by_id";
    private static final String ADD_CATEGORY_TO_PRODUCT = "add_category_to_product";
    private static final String DELETE_CATEGORY_BY_ID = "delete_category_by_id";
    private static final String DELETE_CATEGORY_BY_PRODUCT_ID = "delete_category_by_product_id";

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @GetMapping(GET_ALL_CATEGORIES)
    public ResponseEntity getAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll().stream().distinct().collect(Collectors.toList());
        if (categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_CATEGORIES_FOUND);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(categories);
    }

    @GetMapping(GET_ALL_CATEGORIES_BY_PRODUCT_ID)
    public ResponseEntity getAllCategoriesByProductId(@RequestParam UUID productId) {
        List<CategoryEntity> foundCategories = categoryRepository.findAllByProductId(productId);

        if (foundCategories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_CATEGORIES_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(foundCategories);
        }
    }

    @GetMapping(GET_CATEGORY_BY_ID)
    public ResponseEntity getCategoryById(@RequestParam UUID id) {
        final Optional<CategoryEntity> foundCategory = categoryRepository.findById(id);
        if (!foundCategory.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMassages.CATEGORY_NOT_FOUND);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(foundCategory.get());
    }


    @SneakyThrows
    @PostMapping(ADD_CATEGORY_TO_PRODUCT)
    public ResponseEntity addCategoryToProduct(@RequestParam UUID productId, @RequestParam String title, @RequestParam MultipartFile icon) {
        Byte[] byteIcon = new Byte[icon.getBytes().length];
        int i = 0;
        for (byte b : icon.getBytes()) {
            byteIcon[i++] = b;
        }

        Optional<ProductEntity> foundProduct = productRepository.findById(productId);

        if (!foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.PRODUCT_NOT_FOUND);
        }

        CategoryEntity category = new CategoryEntity();
        category.setTitle(title);
        category.setIcon(byteIcon);
        category.setProduct(foundProduct.get());

        categoryRepository.save(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMassages.PRODUCT_CATEGORY_ADDED);

    }

    @DeleteMapping(DELETE_CATEGORY_BY_ID)
    public ResponseEntity deleteCategory(@RequestParam UUID id) {

        Optional<CategoryEntity> foundCategory = categoryRepository.findById(id);

        if (!foundCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.CATEGORY_DOES_NOT_EXIST);
        } else {
            categoryRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMassages.CATEGORY_DELETED);

        }
    }


    @DeleteMapping(DELETE_CATEGORY_BY_PRODUCT_ID)
    public ResponseEntity deleteCategoriesByProductId(@RequestParam UUID productId) {
        Optional<CategoryEntity> foundCategory = categoryRepository.findByProductId(productId);

        return null;
    }


}
