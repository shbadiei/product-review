package com.codechallenge.product.sales.repository;

import com.codechallenge.product.sales.model.entity.Provider;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends MongoRepository<Provider, ObjectId> {
}
