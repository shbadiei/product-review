package com.codechallenge.product.inventory.model.repository;

import com.codechallenge.product.inventory.model.entity.Product;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, ObjectId> {

    default Page<Product> findProduct(Pageable pageReq) {
        return findAll(pageReq);
    }


}
