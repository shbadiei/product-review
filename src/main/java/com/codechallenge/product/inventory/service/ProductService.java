package com.codechallenge.product.inventory.service;

import com.codechallenge.product.inventory.dto.ProductDto;
import com.codechallenge.product.inventory.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductService {

    Page<ProductDto> find(Product example, PageRequest pageRequest);


}
