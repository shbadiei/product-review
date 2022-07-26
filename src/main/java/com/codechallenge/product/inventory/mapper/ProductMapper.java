package com.codechallenge.product.inventory.mapper;

import com.codechallenge.product.common.mapper.BaseMapper;
import com.codechallenge.product.inventory.dto.ProductDto;
import com.codechallenge.product.inventory.model.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper extends BaseMapper<Product, ProductDto> {

    default List<ProductDto> toFilledDto(List<Product> products){
        if(CollectionUtils.isEmpty(products)){
            return new ArrayList<>();
        }
        List<ProductDto> productDtos = toDtoList(products);
        productDtos.stream().map(ProductDto::getId).collect(Collectors.toList());
        //todo
        return productDtos;
    }

}
