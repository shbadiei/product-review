package com.codechallenge.product.sales.model.entity;

import com.codechallenge.product.inventory.model.entity.Product;
import com.codechallenge.product.uaa.model.entity.UserInfo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Purchase {

    @DBRef
    private Product product;
    @DBRef
    private Provider provider;
    @DBRef
    private UserInfo buyer;

    private Long priceInToman;

    private Date operationDateTime;

}
