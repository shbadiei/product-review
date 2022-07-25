package com.codechallenge.product.inventory.model.repository;

import com.codechallenge.product.inventory.model.entity.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, ObjectId> {
}
