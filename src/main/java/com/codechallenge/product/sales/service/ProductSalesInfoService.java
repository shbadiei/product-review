package com.codechallenge.product.sales.service;

import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductSalesInfoService {

    Page<ProductSalesInfoDto> find(ProductSalesInfoDto salesInfoExample,PageRequest pageRequest);

    List<ProductSalesInfoDto> findByProductIds(List<ObjectId> productIds);

}
