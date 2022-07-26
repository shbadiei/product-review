package com.codechallenge.product.sales.model.entity;

import com.codechallenge.product.common.audit.Auditable;
import com.codechallenge.product.inventory.model.entity.Product;
import com.codechallenge.product.uaa.model.entity.UserInfo;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Data
public class Purchase extends Auditable {

    @DBRef
    private Product product;
    @DBRef
    private Provider provider;
    @DBRef
    private UserInfo buyer;

    private Long priceInToman;

    private Date operationDateTime;

}
