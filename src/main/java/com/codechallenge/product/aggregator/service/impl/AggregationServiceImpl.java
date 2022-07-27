package com.codechallenge.product.aggregator.service.impl;

import com.codechallenge.product.aggregator.dto.request.ProductInfoSearchRequestDto;
import com.codechallenge.product.aggregator.dto.response.FullProductInfoDto;
import com.codechallenge.product.aggregator.mapper.ProductDto2FullMapper;
import com.codechallenge.product.aggregator.mapper.ProductDto2FullProductInfoStatefulConverter;
import com.codechallenge.product.aggregator.service.AggregationService;
import com.codechallenge.product.inventory.dto.ProductDto;
import com.codechallenge.product.inventory.service.ProductService;
import com.codechallenge.product.sales.service.ProductSalesInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AggregationServiceImpl implements AggregationService {

    private final ProductService productService;

    private final ProductSalesInfoService productSalesInfoService;

    private final ProductDto2FullMapper productDto2FullMapper;

    @Override
    public Page<FullProductInfoDto> find(ProductInfoSearchRequestDto searchRequestDto, PageRequest pageRequest) {
        Page<ProductDto> productDtoPage = productService.find(
                new ProductDto()
                        .setId(searchRequestDto.getProductId())
                        .setCategory(searchRequestDto.getCategory())
                        .setTitle(searchRequestDto.getProductTitleLike()),
                pageRequest
        );
        ProductDto2FullProductInfoStatefulConverter converter = new ProductDto2FullProductInfoStatefulConverter(
                productSalesInfoService,
                productDto2FullMapper,
                productDtoPage.getContent()
        );

        return productDtoPage.map(converter::convert);
    }


}
