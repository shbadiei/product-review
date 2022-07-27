package com.codechallenge.product.sales.rest;

import com.codechallenge.product.sales.model.entity.ReviewAccessibilitySetting;
import com.codechallenge.product.sales.service.ProductSalesInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sales/api/v1")
@RequiredArgsConstructor
public class ReviewAccessibilitySettingResource {

    private final ProductSalesInfoService productSalesInfoService;

    @PutMapping("/reviewsetting/{salesInfoId}")
    public ResponseEntity<Void> setReviewAccessibilitySettingForSalesInfo(
            @PathVariable("salesInfoId") String salesInfoId,
            @RequestBody @Valid ReviewAccessibilitySetting setting) {
        productSalesInfoService.setReviewAccessibilitySettingForSalesInfo(salesInfoId, setting);
        return ResponseEntity.noContent().build();
    }

}
