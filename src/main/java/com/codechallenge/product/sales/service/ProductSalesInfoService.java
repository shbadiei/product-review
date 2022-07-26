package com.codechallenge.product.sales.service;

import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductSalesInfoService {

    Page<ProductSalesInfoDto> find(ProductSalesInfoDto salesInfoExample,PageRequest pageRequest);

}
