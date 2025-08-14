package com.emercurius.productservice.repositories.impl;

import com.emercurius.commonlibs.dto.product.ProductFilter;
import com.emercurius.productservice.entities.Product;
import com.emercurius.productservice.repositories.ProductCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Product> findAllByFilter(ProductFilter filter, Pageable pageable) {
        List<Criteria> criteriaList = new ArrayList<>();

        if (filter.name() != null && !filter.name().isBlank()) {
            criteriaList.add(Criteria.where("name").regex(filter.name(), "i"));
        }
        if (filter.categoryId() != null) {
            criteriaList.add(Criteria.where("category.$id").is(filter.categoryId()));
        }
        if (filter.minPrice() != null) {
            criteriaList.add(Criteria.where("price").gte(filter.minPrice()));
        }
        if (filter.maxPrice() != null) {
            criteriaList.add(Criteria.where("price").lte(filter.maxPrice()));
        }
        if (filter.active() != null) {
            criteriaList.add(Criteria.where("active").is(filter.active()));
        }
        if (filter.tags() != null && !filter.tags().isEmpty()) {
            criteriaList.add(Criteria.where("tags").all(filter.tags()));
        }

        Query query = new Query();
        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        long total = mongoTemplate.count(query, Product.class);
        query.with(pageable);

        List<Product> products = mongoTemplate.find(query, Product.class);
        return new PageImpl<>(products, pageable, total);
    }

}
