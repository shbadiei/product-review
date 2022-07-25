package com.codechallenge.product.inventory.service.impl;

import com.codechallenge.product.inventory.dto.ProductDto;
import com.codechallenge.product.inventory.mapper.ProductMapper;
import com.codechallenge.product.inventory.model.repository.ProductRepository;
import com.codechallenge.product.inventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public Page<ProductDto> find(PageRequest pageRequest) {
        final List<ObjectId> productIdList = new ArrayList<>();
        Page<ProductDto> pureProductDtoPage = productRepository.findProduct(pageRequest).map(productMapper::toDto);
        pureProductDtoPage.stream().map(productDto -> productDto.getId());
        //todo
        return null;
    }
}
