package com.codechallenge.product.sales.service;

import com.codechallenge.product.aggregator.mapper.RowLevelSecurityMode;
import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductSalesInfoService {

    Page<ProductSalesInfoDto> find(RowLevelSecurityMode rlsMode, ProductSalesInfoDto salesInfoExample, PageRequest pageRequest);

    List<ProductSalesInfoDto> findByProductIds(RowLevelSecurityMode rlsMode,List<ObjectId> productIds);

}
