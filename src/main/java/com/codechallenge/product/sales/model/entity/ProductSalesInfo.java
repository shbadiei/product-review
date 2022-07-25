package com.codechallenge.product.sales.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Document("productSalesInfo")
@Data
@Accessors(chain = true)
public class ProductSalesInfo implements Serializable {

    @Id
    private ObjectId id;

    @NotNull
    private ObjectId productId;

    @NotNull
    @DBRef
    private Provider provider;

    private ReviewAccessibilitySetting reviewAccessibilitySetting;

    @Positive
    private Long priceInTomans;

}
