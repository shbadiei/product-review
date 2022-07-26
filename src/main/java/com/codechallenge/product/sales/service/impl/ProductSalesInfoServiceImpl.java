package com.codechallenge.product.sales.service.impl;

import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import com.codechallenge.product.sales.mapper.ProductSalesInfoAdapter;
import com.codechallenge.product.sales.mapper.ProductSalesInfoMapper;
import com.codechallenge.product.sales.repository.ProductSalesInfoRepository;
import com.codechallenge.product.sales.service.ProductSalesInfoService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSalesInfoServiceImpl implements ProductSalesInfoService {

    private final ProductSalesInfoRepository productSalesInfoRepository;

    private final ProductSalesInfoMapper productSalesInfoMapper;

    private final ProductSalesInfoAdapter productSalesInfoAdapter;

    @Override
    public Page<ProductSalesInfoDto> find(ProductSalesInfoDto salesInfoExample, PageRequest pageRequest) {
        return productSalesInfoRepository.findAll(
                productSalesInfoMapper.toEntity(salesInfoExample),
                pageRequest
        ).map(productSalesInfoAdapter::adapt);
    }

    @Override
    public List<ProductSalesInfoDto> findByProductIds(List<ObjectId> productIds) {
        return productSalesInfoRepository.findByProductIds(productIds).stream()
                .map(productSalesInfoAdapter::adapt)
                .collect(Collectors.toList());
    }
}
