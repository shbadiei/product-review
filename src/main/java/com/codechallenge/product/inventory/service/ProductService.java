package com.codechallenge.product.inventory.service;

import com.codechallenge.product.inventory.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductService {

    Page<ProductDto> find(ProductDto example, PageRequest pageRequest);

}
