package com.codechallenge.product.sales.dto;

import com.codechallenge.product.sales.model.entity.Provider;
import lombok.Data;

@Data
public class SalesInfoDto {

    private Provider provider;

    private Long priceInToman;

}
