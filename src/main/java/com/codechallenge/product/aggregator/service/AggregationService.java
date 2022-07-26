package com.codechallenge.product.aggregator.service;

import com.codechallenge.product.aggregator.dto.request.ProductInfoSearchRequestDto;
import com.codechallenge.product.aggregator.dto.response.FullProductInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface AggregationService {

    Page<FullProductInfoDto> find(ProductInfoSearchRequestDto searchRequestDto, PageRequest pageRequest);

}
