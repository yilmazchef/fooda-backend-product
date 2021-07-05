package be.fooda.backend.product.controller;

import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.dao.TagRepository;
import be.fooda.backend.product.model.dto.CreateTagRequest;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.model.entity.TagEntity;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.model.http.HttpSuccessMassages;
import be.fooda.backend.product.mapper.TagMapper;
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
@RequestMapping("/tag")
public class TagController {

    private static final String PAGE_SIZE_PER_RESULT = "25";
    private static final String GET_ALL_TAGS = "get_all_tags";
    private static final String GET_ALL_TAGS_BY_PRODUCT_ID = "get_all_tags_by_product_id";
    private static final String ADD_TAG_TO_PRODUCT = "add_tag_to_product";
    private static final String DELETE_TAG_BY_ID = "delete_tag_by_id";
    private static final String DELETE_TAG_BY_PRODUCT_ID = "delete_tag_by_product_id";

    private final TagRepository tagRepository;
    private final ProductRepository productRepository;
    private final TagMapper tagMapper;


    @GetMapping(GET_ALL_TAGS)
    public ResponseEntity getAllTags() {
        List<TagEntity> tags = tagRepository.findAll().stream().distinct().collect(Collectors.toList());
        if (tags.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_TAGS_FOUND);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(tags);
    }

    @GetMapping(GET_ALL_TAGS_BY_PRODUCT_ID)
    public ResponseEntity getAllTagByProductId(@RequestParam UUID productId) {
        List<TagEntity> foundTags = tagRepository.findAllByProductId(productId);

        if (foundTags.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.NO_TAGS_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(foundTags);
        }
    }

    @PostMapping("add_tag")
    public ResponseEntity addTag(@RequestBody @Valid CreateTagRequest tagCreate) {

        final TagEntity tag = tagMapper.toEntity(tagCreate);

        if (tagRepository.existByUniqueFields(tag.getValue())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMassages.TAG_ALREADY_EXIST);
        }
        tagRepository.save(tag);

        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMassages.TAG_ADDED);
    }

    @PostMapping(ADD_TAG_TO_PRODUCT)
    public ResponseEntity addTagToProduct(@RequestParam UUID productId, @RequestParam String value) {

        Optional<ProductEntity> foundProduct = productRepository.findById(productId);

        if (!foundProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.PRODUCT_NOT_FOUND);

        TagEntity tag = new TagEntity();
        tag.setValue(value);
        tag.setProduct(foundProduct.get());

        tagRepository.save(tag);

        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMassages.PRODUCT_TAG_ADDED);

    }

    @DeleteMapping(DELETE_TAG_BY_ID)
    public ResponseEntity deleteTag(@RequestParam UUID id) {

        Optional<TagEntity> foundTag = tagRepository.findById(id);

        if (!foundTag.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.TAG_DOES_NOT_EXIST);
        } else {
            tagRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMassages.TAG_DELETED);

        }
    }
}
