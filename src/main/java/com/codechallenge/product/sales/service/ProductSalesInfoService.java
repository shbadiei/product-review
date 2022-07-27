package com.codechallenge.product.sales.service;

import com.codechallenge.product.aggregator.mapper.RowLevelSecurityMode;
import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import com.codechallenge.product.sales.model.entity.ReviewAccessibilitySetting;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface ProductSalesInfoService {

    Page<ProductSalesInfoDto> find(RowLevelSecurityMode rlsMode, ProductSalesInfoDto salesInfoExample, PageRequest pageRequest);

    List<ProductSalesInfoDto> findByProductIds(RowLevelSecurityMode rlsMode, List<ObjectId> productIds);

    void setReviewAccessibilitySettingForSalesInfo(String salesInfoId, ReviewAccessibilitySetting setting);

}
