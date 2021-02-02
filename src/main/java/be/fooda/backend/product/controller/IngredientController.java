package be.fooda.backend.product.controller;

import be.fooda.backend.product.dao.IngredientRepository;
import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.model.create.IngredientCreate;
import be.fooda.backend.product.model.entity.IngredientEntity;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.model.http.HttpSuccessMassages;
import be.fooda.backend.product.service.mapper.IngredientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private static final String PAGE_SIZE_PER_RESULT = "25";
    private static final String GET_ALL_INGREDIENTS = "get_all_tags";
    private static final String GET_ALL_INGREDIENTS_BY_PRODUCT_ID = "get_all_ingredients_by_product_id";
    private static final String ADD_INGREDIENT_TO_PRODUCT = "add_ingredient_to_product";
    private static final String ADD_INGREDIENT = "add_ingredient";
    private static final String DELETE_INGREDIENT_BY_ID = "delete_ingredient_by_id";
    private static final String DELETE_INGREDIENTS_BY_PRODUCT_ID = "delete_ingredient_by_product_id";

    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;
    private final IngredientMapper ingredientMapper;

    @GetMapping(GET_ALL_INGREDIENTS_BY_PRODUCT_ID)
    public ResponseEntity getAllIngredientByProductId(@RequestParam UUID productId) {

        List<IngredientEntity> foundIngredients = ingredientRepository.findAllByProductId(productId);

        if (foundIngredients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_INGREDIENTS_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(foundIngredients);
        }
    }

    @GetMapping(GET_ALL_INGREDIENTS)
    public ResponseEntity getAllIngredients() {
        List<IngredientEntity> ingredients = ingredientRepository.findAll().stream().distinct().collect(Collectors.toList());
        if (ingredients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_INGREDIENTS_FOUND);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(ingredients);
    }

    @PostMapping(ADD_INGREDIENT_TO_PRODUCT)
    public ResponseEntity addIngredientToProduct(@RequestParam UUID productId, @RequestParam String ingredientName) {

        Optional<ProductEntity> foundProduct = productRepository.findById(productId);

        if (!foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.PRODUCT_NOT_FOUND);
        }

        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setIngredientName(ingredientName);
        ingredient.setProduct(foundProduct.get());

        ingredientRepository.save(ingredient);

        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMassages.PRODUCT_INGREDIENT_ADDED);

    }

    @PostMapping(ADD_INGREDIENT)
    public ResponseEntity create(@RequestBody @Valid IngredientCreate ingredientCreate) {

        final IngredientEntity ingredient = ingredientMapper.toEntity(ingredientCreate);

        if (ingredientRepository.existByUniqueFields(ingredient.getIngredientName(), ingredient.getPrice())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMassages.INGREDIENT_ALREADY_EXIST);
        }
        ingredientRepository.save(ingredient);

        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMassages.INGREDIENT_ADDED);
    }

    @DeleteMapping(DELETE_INGREDIENT_BY_ID)
    public ResponseEntity deleteIngredientById(@RequestParam UUID id) {
        Optional<IngredientEntity> foundIngredient = ingredientRepository.findById(id);

        if (!foundIngredient.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.INGREDIENT_DOES_NOT_EXIST);

        ingredientRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMassages.INGREDIENT_DELETED);


    }

    @DeleteMapping(DELETE_INGREDIENTS_BY_PRODUCT_ID)
    public ResponseEntity deleteIngredientByProductId(@RequestParam UUID productId) {

        return null;

    }
}
