package com.emercurius.productservice.repositories;

import com.emercurius.productservice.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
