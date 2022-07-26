package com.codechallenge.product.sales.service.impl;

import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import com.codechallenge.product.sales.mapper.ProductSalesInfoMapper;
import com.codechallenge.product.sales.repository.ProductSalesInfoRepository;
import com.codechallenge.product.sales.service.ProductSalesInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductSalesInfoServiceImpl implements ProductSalesInfoService {

    private final ProductSalesInfoRepository productSalesInfoRepository;

    private final ProductSalesInfoMapper productSalesInfoMapper;

    @Override
    public Page<ProductSalesInfoDto> find(ProductSalesInfoDto salesInfoExample, PageRequest pageRequest) {
        return productSalesInfoRepository.findAll(
                productSalesInfoMapper.toEntity(salesInfoExample),
                pageRequest
        ).map(productSalesInfoMapper::toDto);
    }
}
