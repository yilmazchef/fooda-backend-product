package be.fooda.backend.product.view.controller;

import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.dao.TaxRepository;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.model.entity.TaxEntity;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.model.http.HttpSuccessMassages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/tax")
public class TaxController {


    private static final String GET_ALL_TAXES = "get_all_taxes";
    private static final String GET_ALL_TAXES_BY_PRODUCT_ID = "get_all_taxes_by_product_id";
    private static final String ADD_TAX_TO_PRODUCT = "add_tax_to_product";
    private static final String DELETE_TAX_BY_ID = "delete_tax_by_id";
    private static final String DELETE_TAX_BY_PRODUCT_ID = "delete_tax_by_product_id";

    private final TaxRepository taxRepository;
    private final ProductRepository productRepository;

    @GetMapping(GET_ALL_TAXES_BY_PRODUCT_ID)
    public ResponseEntity getAllTaxByProductId(@RequestParam UUID productId) {
        List<TaxEntity> foundTaxes = taxRepository.findAllByProductId(productId);

        if (foundTaxes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_TAGS_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(foundTaxes);
        }
    }

    @PostMapping(ADD_TAX_TO_PRODUCT)
    public ResponseEntity addTaxToProduct(@RequestParam UUID productId, @RequestParam String title,
                                          @RequestParam Double percentage, @RequestParam Boolean isDefault) {

        Optional<ProductEntity> foundProduct = productRepository.findById(productId);

        if (!foundProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.PRODUCT_NOT_FOUND);

        TaxEntity tax = new TaxEntity();
        tax.setTitle(title);
        tax.setPercentage(percentage);
        tax.setIsDefault(isDefault);
        tax.setProduct(foundProduct.get());

        taxRepository.save(tax);

        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMassages.PRODUCT_TAX_ADDED);

    }

    @DeleteMapping(DELETE_TAX_BY_ID)
    public ResponseEntity deleteTax(@RequestParam UUID id) {

        Optional<TaxEntity> foundTax = taxRepository.findById(id);

        if (!foundTax.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.TAX_DOES_NOT_EXIST);
        } else {
            taxRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMassages.TAX_DELETED);

        }
    }
}
