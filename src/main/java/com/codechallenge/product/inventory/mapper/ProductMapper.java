package com.codechallenge.product.inventory.mapper;

import com.codechallenge.product.common.mapper.BaseMapper;
import com.codechallenge.product.inventory.dto.ProductDto;
import com.codechallenge.product.inventory.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product, ProductDto> {

}
