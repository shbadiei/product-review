package com.codechallenge.product.inventory.service.impl;

import com.codechallenge.product.inventory.dto.ProductDto;
import com.codechallenge.product.inventory.mapper.ProductMapper;
import com.codechallenge.product.inventory.model.entity.Product;
import com.codechallenge.product.inventory.model.repository.ProductRepository;
import com.codechallenge.product.inventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public Page<ProductDto> find(Product example, PageRequest pageRequest) {
        return productRepository.findProduct(example, pageRequest).map(productMapper::toDto);
    }
}
