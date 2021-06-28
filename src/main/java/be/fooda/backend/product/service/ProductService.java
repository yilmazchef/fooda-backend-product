package be.fooda.backend.product.service;

import be.fooda.backend.product.dao.*;
import be.fooda.backend.product.mapper.CategoryMapper;
import be.fooda.backend.product.mapper.IngredientMapper;
import be.fooda.backend.product.mapper.ProductMapper;
import be.fooda.backend.product.model.entity.CategoryEntity;
import be.fooda.backend.product.model.entity.IngredientEntity;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.model.request.CategoryCreate;
import be.fooda.backend.product.model.request.IngredientCreate;
import be.fooda.backend.product.model.request.ProductCreate;
import be.fooda.backend.product.model.response.ProductResponse;
import com.google.common.collect.ImmutableList;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    IngredientRepository ingredientRepository;
    MediaRepository mediaRepository;
    PriceRepository priceRepository;
    StoreRepository storeRepository;
    TagRepository tagRepository;
    TaxRepository taxRepository;

    ProductMapper productMapper;
    CategoryMapper categoryMapper;
    IngredientMapper ingredientMapper;

    Optional<ProductResponse> addProduct(ProductCreate product) {

        final var productEntity = new ProductEntity();

        // SAVE_CATEGORIES_TO_DB
        final var categoriesBuilder = ImmutableList.<CategoryEntity>builder();
        for (CategoryCreate category : product.getCategories()) {
            CategoryEntity categoryEntity = categoryMapper.toEntity(category);
            final var savedCategory = categoryRepository.save(categoryEntity);
            categoriesBuilder.add(savedCategory);
        }
        final var categoryEntities = categoriesBuilder.build();
        productEntity.setCategories(categoryEntities);

        final var ingredientsBuilder = ImmutableList.builder();
        for (IngredientCreate ingredient : product.getIngredients()) {
            IngredientEntity ingredientEntity = ingredientMapper.toEntity(ingredient);
            ingredientRepository.save(ingredientEntity);

        }


        return Optional.ofNullable(productMapper.toResponse(productEntity));
    }

}
