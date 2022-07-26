package com.codechallenge.product.sales.repository;

import com.codechallenge.product.sales.model.entity.ProductSalesInfo;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSalesInfoRepository extends MongoRepository<ProductSalesInfo, ObjectId> {

    default Page<ProductSalesInfo> findAll(ProductSalesInfo salesInfoExample, Pageable pageable){
        return findAll(Example.of(salesInfoExample),pageable);
    }

}
