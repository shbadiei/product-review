package com.codechallenge.product.sales.service.impl;

import com.codechallenge.product.aggregator.mapper.RowLevelSecurityMode;
import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import com.codechallenge.product.sales.mapper.ProductSalesInfoConverter;
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

    private final ProductSalesInfoConverter productSalesInfoConverter;

    @Override
    public Page<ProductSalesInfoDto> find(RowLevelSecurityMode rlsMode, ProductSalesInfoDto salesInfoExample, PageRequest pageRequest) {
        return productSalesInfoRepository.findAll(
                productSalesInfoMapper.toEntity(salesInfoExample),
                pageRequest
        ).map(salesInfo -> productSalesInfoConverter.convert(rlsMode, salesInfo));
    }

    @Override
    public List<ProductSalesInfoDto> findByProductIds(RowLevelSecurityMode rlsMode, List<ObjectId> productIds) {
        return productSalesInfoRepository.findByProductIds(productIds).stream()
                .map(salesInfo -> productSalesInfoConverter.convert(rlsMode, salesInfo))
                .collect(Collectors.toList());
    }
}
