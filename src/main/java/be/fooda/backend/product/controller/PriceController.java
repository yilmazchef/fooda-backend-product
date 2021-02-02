package be.fooda.backend.product.controller;

import be.fooda.backend.product.dao.PriceRepository;
import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.model.entity.PriceEntity;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.model.http.HttpSuccessMassages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/price")
public class PriceController {

    private static final String PAGE_SIZE_PER_RESULT = "25";

    private static final String GET_ALL_PRICES = "get_all_tags";
    private static final String GET_ALL_PRICES_BY_PRODUCT_ID = "get_all_prices_by_product_id";
    private static final String ADD_PRICE_TO_PRODUCT = "add_price_to_to_product";
    private static final String ADD_PRICE = "add_ingredient";
    private static final String DELETE_PRICE_BY_ID = "delete_price_by_id";
    private static final String DELETE_PRICE_BY_PRODUCT_ID = "delete_price_by_product_id";

    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;

    @GetMapping(GET_ALL_PRICES_BY_PRODUCT_ID)
    public ResponseEntity getAllPricesByProductId(@RequestParam UUID productId) {
        List<PriceEntity> foundPrices = priceRepository.findAllByProductId(productId);

        if (foundPrices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_PRODUCT_PRICE_IS_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(foundPrices);
        }
    }

    @PostMapping(ADD_PRICE_TO_PRODUCT)
    public ResponseEntity addPriceToProduct(@RequestParam UUID productId, @RequestParam BigDecimal amount,
                                            @RequestParam String title) {

        Optional<ProductEntity> foundProduct = productRepository.findById(productId);

        if (!foundProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.PRODUCT_NOT_FOUND);

        PriceEntity price = new PriceEntity();
        price.setTitle(title);
        price.setAmount(amount);
        price.setProduct(foundProduct.get());

        priceRepository.save(price);

        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMassages.PRODUCT_PRICE_ADDED);

    }

    @DeleteMapping(DELETE_PRICE_BY_ID)
    public ResponseEntity deletePriceById(@RequestParam UUID id) {

        Optional<PriceEntity> foundPrice = priceRepository.findById(id);

        if (!foundPrice.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.CATEGORY_DOES_NOT_EXIST);
        } else {
            priceRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMassages.CATEGORY_DELETED);

        }
    }

    @DeleteMapping(DELETE_PRICE_BY_PRODUCT_ID)
    public ResponseEntity deletePricesByProductId(@RequestParam UUID productId) {
        Optional<PriceEntity> foundPrice = priceRepository.findByProductId(productId);

        return null;
    }

}
