package com.emercurius.productservice.repositories;

import com.emercurius.productservice.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
