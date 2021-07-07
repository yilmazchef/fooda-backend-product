package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.ProductEntity;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ProductIndexer {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public Page<ProductEntity> search(Pageable pageable, String keyword) {
        SearchSession session = Search.session(entityManager);

        SearchResult<ProductEntity> result = session.search(ProductEntity.class)
                .where(f -> f.match().fields("name").matching(keyword).fuzzy())
                .fetch((int) pageable.getOffset(), pageable.getPageSize());

        return new PageImpl<>(result.hits(), pageable, result.total().hitCount());

    }


}
