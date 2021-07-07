package be.fooda.backend.product.config;

import be.fooda.backend.product.model.entity.ProductEntity;
import org.hibernate.search.mapper.orm.Search;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class IndexOnStartup implements CommandLineRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public void run(String... args) throws Exception {
        final var searchSession = Search.session(entityManager);
        final var indexer = searchSession.massIndexer(ProductEntity.class);
        indexer.startAndWait();
    }
}
