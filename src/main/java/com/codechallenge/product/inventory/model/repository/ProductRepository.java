package com.codechallenge.product.inventory.model.repository;

import com.codechallenge.product.inventory.model.entity.Product;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    default Page<Product> findProduct(Product example, Pageable pageReq) {
        return findAll(Example.of(example), pageReq);
    }

}
