package com.codechallenge.product.aggregator.dto.response;

import com.codechallenge.product.inventory.dto.ProductDto;
import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FullProductInfoDto extends ProductDto {

    private List<ProductSalesInfoDto> salesInfo;

}
