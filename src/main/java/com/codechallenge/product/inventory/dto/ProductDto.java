package com.codechallenge.product.inventory.dto;

import com.codechallenge.product.inventory.model.enumuration.ProductCategory;
import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;

@Data
@Accessors(chain = true)
public class ProductDto {

    private ObjectId id;

    private String title;

    private ProductCategory category;

}
