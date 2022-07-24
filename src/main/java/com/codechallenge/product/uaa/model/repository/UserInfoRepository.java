package com.codechallenge.product.uaa.model.repository;

import com.codechallenge.product.uaa.model.entity.UserInfo;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, UUID> {

    @Query("{username: ?0}")
    Optional<UserInfo> findByUsername(String username);
}
