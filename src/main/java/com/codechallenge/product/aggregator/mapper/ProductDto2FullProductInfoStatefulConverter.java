package com.codechallenge.product.aggregator.mapper;

import com.codechallenge.product.aggregator.dto.response.FullProductInfoDto;
import com.codechallenge.product.inventory.dto.ProductDto;
import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import com.codechallenge.product.sales.model.entity.ReviewAccessibilitySetting;
import com.codechallenge.product.sales.model.enumuration.Visibility;
import com.codechallenge.product.sales.service.ProductSalesInfoService;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Warning: This class is Stateful
 */
public class ProductDto2FullProductInfoStatefulConverter {

    final Map<ObjectId, List<ProductSalesInfoDto>> productId2SalesInfos;

    final ProductDto2FullMapper productDto2FullMapper;

    final RowLevelSecurityMode rlsMode;

    public ProductDto2FullProductInfoStatefulConverter(
            RowLevelSecurityMode rlsMode,
            ProductSalesInfoService productSalesInfoService,
            ProductDto2FullMapper productDto2FullMapper,
            List<ProductDto> productDtos) {

        this.rlsMode = rlsMode;
        this.productDto2FullMapper = productDto2FullMapper;

        productId2SalesInfos = productSalesInfoService.findByProductIds(
                        rlsMode,
                        productDtos.stream().map(ProductDto::getId).collect(Collectors.toList())
                ).stream()
                .filter(salesInfo -> filterSalesInfoByRLSMode(rlsMode, salesInfo))
                .collect(Collectors.groupingBy(
                        ProductSalesInfoDto::getProductId,
                        Collectors.toList()
                ));
    }

    private boolean filterSalesInfoByRLSMode(RowLevelSecurityMode rlsMode, ProductSalesInfoDto salesInfo) {
        if (rlsMode == RowLevelSecurityMode.CUSTOMER) {
            ReviewAccessibilitySetting setting = salesInfo.getReviewAccessibilitySetting();
            return (setting != null && setting.getProductVisibility() != null && setting.getProductVisibility() == Visibility.VISIBLE);
        } else {
            return rlsMode == RowLevelSecurityMode.SALES;
        }
    }

    public FullProductInfoDto convert(ProductDto productDto) {
        return productDto2FullMapper.toDto(productDto).setSalesInfo(productId2SalesInfos.get(productDto.getId()));
    }
}
