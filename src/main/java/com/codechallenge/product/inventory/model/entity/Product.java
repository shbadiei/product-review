package com.codechallenge.product.inventory.model.entity;

import com.codechallenge.product.inventory.model.enumuration.ProductCategory;
import com.codechallenge.product.sales.model.entity.ProductSalesInfo;
import com.codechallenge.product.sales.model.entity.Vote;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document("product")
@Data
@Accessors(chain = true)
public class Product {

    @Id
    private ObjectId id;
    @NotBlank
    private String title;
    @NotNull
    private ProductCategory category;

    @DBRef
    @JsonManagedReference
    private List<ProductSalesInfo> salesInfos;

}
