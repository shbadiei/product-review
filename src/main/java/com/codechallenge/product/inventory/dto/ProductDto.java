package com.codechallenge.product.inventory.dto;

import com.codechallenge.product.inventory.model.enumuration.ProductCategory;
import com.codechallenge.product.sales.model.entity.ProductSalesInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProductDto {

    private ObjectId id;

    private String title;

    private ProductCategory category;

    @JsonManagedReference
    private List<ProductSalesInfo> salesInfos;

}
