package com.codechallenge.product.sales.dto;

import lombok.Data;
import org.bson.types.ObjectId;
import javax.validation.constraints.NotNull;

@Data
public class ProviderDto {

    private ObjectId id;

    @NotNull
    private String companyTitle;

}
