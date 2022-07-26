package com.codechallenge.product.sales.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Purchase {

    private ObjectId productId;

    @DBRef
    private Provider provider;

    private String buyerUsername;

    private Long priceInToman;

    private Date operationDateTime;

}
