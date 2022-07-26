package com.codechallenge.product.aggregator.dto;

import com.codechallenge.product.inventory.dto.ProductDto;
import com.codechallenge.product.sales.dto.SalesInfoDto;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FullProductInfoDto extends ProductDto {

    private SalesInfoDto salesInfo;

    public FullProductInfoDto(ProductDto productInfo, SalesInfoDto salesInfo) {

        this.salesInfo = salesInfo;
    }
}
