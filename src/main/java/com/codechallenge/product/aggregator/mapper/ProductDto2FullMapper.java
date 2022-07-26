package com.codechallenge.product.aggregator.mapper;

import com.codechallenge.product.aggregator.dto.response.FullProductInfoDto;
import com.codechallenge.product.common.mapper.BaseMapper;
import com.codechallenge.product.inventory.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProductDto2FullMapper extends BaseMapper<ProductDto, FullProductInfoDto> {

}
