package com.codechallenge.product.sales.repository;

import com.codechallenge.product.inventory.model.enumuration.VerificationStatus;
import com.codechallenge.product.sales.model.entity.Comment;
import com.codechallenge.product.sales.model.entity.ProductSalesInfo;
import org.bson.types.ObjectId;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, ObjectId> {

    Integer NUMBER_OF_LAST_COMMITS = 3;

    default Page<Comment> findLatestCommentsForProductSalesInfo(
            ProductSalesInfo productSalesInfo,
            VerificationStatus verificationStatus) {
        return findAll(
                Example.of(
                        new Comment().setProductSalesInfo(productSalesInfo).setVerificationStatus(verificationStatus),
                        ExampleMatcher.matching()),
                PageRequest.of(0, NUMBER_OF_LAST_COMMITS, Sort.by(Sort.Direction.DESC, "creationDate"))
        );
    }

}
