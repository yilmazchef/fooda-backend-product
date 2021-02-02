package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ProductSearch {

    private final EntityManager entityManager;

    private static final Integer RESULTS_PER_PAGE = 25;

    private FullTextEntityManager entityManager() {
        return Search.getFullTextEntityManager(entityManager);
    }

    @Transactional
    public List<ProductEntity> combined(Set<String> keywords, int pageNo, int pageSize, boolean isActive) {

        FullTextEntityManager fullTextEntityManager = entityManager();

        QueryBuilder queryBuilder =
                getQueryBuilder(fullTextEntityManager);

        BooleanJunction<BooleanJunction> combinedJunction = queryBuilder.bool();
        List<Query> queries = keywords.stream()
                .map(keyword -> {
                    Query nameQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("name")
                            .matching(keyword)
                            .createQuery();

                    Query descriptionQuery = queryBuilder
                            .phrase()
                            .withSlop(1)
                            .onField("description")
                            .sentence(keyword)
                            .createQuery();

                    Query storeQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("store.name")
                            .matching(keyword)
                            .createQuery();

                    Query titleQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("prices.title")
                            .matching(keyword)
                            .createQuery();

                    Query amountQuery = queryBuilder
                            .range()
                            .onField("prices.amount")
                            .from(0).to(3).excludeLimit()
                            .createQuery();

                    Query currencyQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("prices.currency")
                            .matching(keyword)
                            .createQuery();

                    Query taxQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("taxes.title")
                            .matching(keyword)
                            .createQuery();

                    Query urlQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("images.url")
                            .matching(keyword)
                            .createQuery();

                    Query categoryQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("categories.title")
                            .matching(keyword)
                            .createQuery();

                    Query tagQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("tags.value")
                            .matching(keyword)
                            .createQuery();

                    Query ingredientQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("ingredients.ingredientName")
                            .matching(keyword)
                            .createQuery();

                    Query isActiveQuery = queryBuilder
                            .keyword()
                            .onField("isActive")
                            .ignoreAnalyzer()
                            .boostedTo(1L)
                            .matching(isActive)
                            .createQuery();

                    // a combined query from all sub queries for each keyword..
                    return queryBuilder
                            .bool()
                            .should(nameQuery)
                            .should(descriptionQuery)
                            .should(storeQuery)
                            .should(titleQuery)
                            .should(amountQuery)
                            .should(currencyQuery)
                            .should(taxQuery)
                            .should(urlQuery)
                            .should(categoryQuery)
                            .should(tagQuery)
                            .should(ingredientQuery)
                            .must(isActiveQuery)
                            .createQuery();
                })
                .collect(Collectors.toList());

        for (Query query : queries) {
            combinedJunction.should(query);
        }

        Query combinedQuery = combinedJunction.createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(combinedQuery, ProductEntity.class);
        //jpaQuery.setMaxResults(pageNo);
        //jpaQuery.setFirstResult(pageSize);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<ProductEntity> results = jpaQuery.getResultList();
        return results;
    }

    @Transactional
    public List<ProductEntity> simple(Set<String> keywords, Pageable pageable) {

        final FullTextEntityManager fullTextEntityManager = entityManager();
        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = getQueryBuilder(fullTextEntityManager);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene query using the builder
                    Query query = qb
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onFields("name", "ingredients.ingredientName", "categories.title", "tags.value", "store.name")
                            .matching(keyword)
                            .createQuery();

                    FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, ProductEntity.class);
                    //fullTextQuery.setMaxResults(RESULTS_PER_PAGE);
                    //fullTextQuery.setFirstResult(1);

                    //returns JPA managed entities
                    return (List<ProductEntity>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ProductEntity> searchProductsByName(String name, int pageNo, int pageSize) {
        FullTextEntityManager fullTextEntityManager = entityManager();

        QueryBuilder queryBuilder =
                getQueryBuilder(fullTextEntityManager);

        Query nameQuery = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("name")
                .matching(name)
                .createQuery();

        FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(nameQuery, ProductEntity.class);
        //jpaQuery.setMaxResults(pageNo);
        //jpaQuery.setFirstResult(pageSize);

        List<ProductEntity> results = jpaQuery.getResultList();
        return results;
    }

    @Transactional
    public List<ProductEntity> searchByDescription(String description, int pageNo, int pageSize) {
        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager = entityManager();

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                getQueryBuilder(fullTextEntityManager);

        Query descriptionQuery = queryBuilder
                .phrase()
                .onField("description")
                .sentence(description)
                .createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(descriptionQuery, ProductEntity.class);
        //jpaQuery.setMaxResults(RESULTS_PER_PAGE);
        //jpaQuery.setFirstResult(1);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<ProductEntity> results = jpaQuery.getResultList();
        return results;
    }


    @Transactional
    public List<ProductEntity> searchByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, int pageNo, int pageSize, boolean isActive) {
        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager = entityManager();

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                getQueryBuilder(fullTextEntityManager);

        Query priceRangeQuery = queryBuilder
                .range()
                .onField("prices.amount")
                .from(minPrice)
                .to(maxPrice).excludeLimit()
                .createQuery();

        Query isActiveQuery = queryBuilder
                .keyword()
                .onField("isActive")
                .matching(isActive)
                .createQuery();

        Query combineQuery = queryBuilder
                .bool()
                .should(priceRangeQuery)
                .should(isActiveQuery)
                .createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(combineQuery, ProductEntity.class);
        // jpaQuery.setMaxResults(pageNo);
        //jpaQuery.setFirstResult(pageSize);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<ProductEntity> results = jpaQuery.getResultList();
        return results;
    }

    @Transactional
    public List<ProductEntity> searchByStoreName(String name, int pageNo, int pageSize) {

        FullTextEntityManager fullTextEntityManager = entityManager();

        QueryBuilder queryBuilder =
                getQueryBuilder(fullTextEntityManager);

        Query nameQuery = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("store.name")
                .matching(name)
                .createQuery();


        FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(nameQuery, ProductEntity.class);
        //jpaQuery.setMaxResults(RESULTS_PER_PAGE);
        //jpaQuery.setFirstResult(1);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<ProductEntity> results = jpaQuery.getResultList();
        return results;
    }

    @Transactional
    public List<ProductEntity> searchByIngredients(Set<String> ingredients, int pageNo, int pageSize) {
        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager = entityManager();

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                getQueryBuilder(fullTextEntityManager);


        BooleanJunction<BooleanJunction> combinedJunction = queryBuilder.bool();
        List<Query> queries = ingredients.stream()
                .map(ingredient -> queryBuilder
                        .keyword()
                        .fuzzy()
                        .withEditDistanceUpTo(2)
                        .withPrefixLength(0)
                        .onField("ingredients.ingredientName")
                        .matching(ingredient)
                        .createQuery())
                .collect(Collectors.toList());

        for (Query query : queries) {
            combinedJunction.should(query);
        }

        Query combinedQuery = combinedJunction.createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(combinedQuery, ProductEntity.class);
        //jpaQuery.setMaxResults(RESULTS_PER_PAGE);
        //jpaQuery.setFirstResult(1);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<ProductEntity> results = jpaQuery.getResultList();
        return results;
    }

    @Transactional
    public List<ProductEntity> searchByCategories(Set<String> categories, int pageNo, int pageSize) {
        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager = entityManager();

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                getQueryBuilder(fullTextEntityManager);

        BooleanJunction<BooleanJunction> combinedJunction = queryBuilder.bool();
        List<Query> queries = categories.stream()
                .map(category -> queryBuilder
                        .keyword()
                        .fuzzy()
                        .withEditDistanceUpTo(2)
                        .withPrefixLength(0)
                        .onField("categories.title")
                        .matching(category)
                        .createQuery())
                .collect(Collectors.toList());

        for (Query query : queries) {
            combinedJunction.should(query);
        }

        Query combinedQuery = combinedJunction.createQuery();
        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(combinedQuery, ProductEntity.class);
        //jpaQuery.setMaxResults(RESULTS_PER_PAGE);
        //jpaQuery.setFirstResult(1);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<ProductEntity> results = jpaQuery.getResultList();
        return results;
    }

    @Transactional
    public List<ProductEntity> searchByTags(Set<String> tags, int pageNo, int pageSize) {
        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager = entityManager();

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                getQueryBuilder(fullTextEntityManager);

        BooleanJunction<BooleanJunction> combinedJunction = queryBuilder.bool();
        List<Query> queries = tags.stream()
                .map(tag -> queryBuilder
                        .keyword()
                        .fuzzy()
                        .withEditDistanceUpTo(2)
                        .withPrefixLength(0)
                        .onField("tags.value")
                        .matching(tag)
                        .createQuery())
                .collect(Collectors.toList());

        for (Query query : queries) {
            combinedJunction.should(query);
        }

        Query combinedQuery = combinedJunction.createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(combinedQuery, ProductEntity.class);
        //jpaQuery.setMaxResults(RESULTS_PER_PAGE);
        //jpaQuery.setFirstResult(1);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<ProductEntity> results = jpaQuery.getResultList();
        return results;
    }

    @Transactional
    public List<ProductEntity> filterFeatured(Boolean isFeatured, int pageNo, int pageSize) {
        FullTextEntityManager fullTextEntityManager = entityManager();

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                getQueryBuilder(fullTextEntityManager);

        Query isFeaturedQuery = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("isFeatured")
                .matching(isFeatured)
                .createQuery();

        FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(isFeaturedQuery, ProductEntity.class);
        //jpaQuery.setMaxResults(RESULTS_PER_PAGE);
        //jpaQuery.setFirstResult(1);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<ProductEntity> results = jpaQuery.getResultList();
        return results;

    }


    private QueryBuilder getQueryBuilder(FullTextEntityManager fullTextEntityManager) {
        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(ProductEntity.class)
                .get();
    }
}
