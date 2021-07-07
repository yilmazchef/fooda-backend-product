package be.fooda.backend.product.service.flow;

import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.dto.ProductResponse;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.service.exception.ResourceNotFoundException;
import be.fooda.backend.product.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductFlow {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /*
        Responsibilities of this class:
        1- All scenarios (requirements) will be implemented here..
        2- Exceptions will be handled here.
        3- Mappers will be used here.
        4- Validations for class fields will be checked here.
        5- Database transactions will be processed here.
     */

    // CREATE_PRODUCT(REQUEST)
    public void createProduct(CreateProductRequest request) throws
            NullPointerException, ResourceNotFoundException {

        // IF(NULL)
        if (Objects.isNull(request)) {
            // THROW_EXCEPTION
            throw new NullPointerException("There is no request in the body.");
        }

        //  IF(PRODUCT_EXISTS)
        boolean exists = productRepository.existsByNameAndStore_StoreId(
                request.getName(), request.getStore().getStoreId()
        );

        if (exists) {
            // THROW_EXCEPTION
            throw new ResourceNotFoundException("Product NOT found");
        }

        // MAP_DTO_TO_ENTITY
        ProductEntity entity = productMapper.toEntity(request);

        // SAVE_TO_DB(ENTITY)
        productRepository.save(entity);

    }

    // UPDATE_PRODUCT(UNIQUE_IDENTIFIER, REQUEST)


    // FIND_ALL(PAGE_NO, PAGE_SIZE)
    public List<ProductResponse> findAll(int pageNo, int pageSize) {

        // READ_FROM_DB(PAGE_NO, PAGE_SIZE)
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ProductEntity> pages = productRepository.findAll(pageable);

        // RETURN
        return productMapper.toResponses(pages.toList());
    }

    public List<ProductEntity> findAllByIsActive(boolean active, Pageable paging) {
        return null;
    }

    public Page<ProductEntity> search(PageRequest pageRequest, String name) {
        return null;
    }

    public Optional<ProductEntity> findById(UUID id) {
        return Optional.empty();
    }


    // FIND_BY_ID


    // FIND_BY_FIRST_NAME_AND_LAST_NAME


}
