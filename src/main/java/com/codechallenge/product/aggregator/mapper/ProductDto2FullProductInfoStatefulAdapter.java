package com.codechallenge.product.aggregator.mapper;

import com.codechallenge.product.aggregator.dto.response.FullProductInfoDto;
import com.codechallenge.product.inventory.dto.ProductDto;
import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import com.codechallenge.product.sales.service.ProductSalesInfoService;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Warning: This class is Stateful
 */
public class ProductDto2FullProductInfoStatefulAdapter {

    final Map<ObjectId, List<ProductSalesInfoDto>> productId2SalesInfos;

    final ProductDto2FullMapper productDto2FullMapper;

    public ProductDto2FullProductInfoStatefulAdapter(
            ProductSalesInfoService productSalesInfoService,
            ProductDto2FullMapper productDto2FullMapper,
            List<ProductDto> productDtos) {

        this.productDto2FullMapper = productDto2FullMapper;

        productId2SalesInfos = productSalesInfoService.findByProductIds(
                productDtos.stream().map(ProductDto::getId).collect(Collectors.toList())
        ).stream().collect(Collectors.groupingBy(
                ProductSalesInfoDto::getProductId,
                Collectors.toList()
        ));
    }

    public FullProductInfoDto adapt(ProductDto productDto) {
        return productDto2FullMapper.toDto(productDto).setSalesInfo(productId2SalesInfos.get(productDto.getId()));
    }
}
