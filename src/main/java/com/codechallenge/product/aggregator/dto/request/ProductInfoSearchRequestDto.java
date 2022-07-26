package com.codechallenge.product.aggregator.dto.request;

import com.codechallenge.product.inventory.model.enumuration.ProductCategory;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;

@Data
public class ProductInfoSearchRequestDto {

    private ObjectId productId;

    private String productTitleLike;

    private ProductCategory category;

    private int page;

    private int size = 10;

    private Sort sort;
}
