package com.codechallenge.product.inventory.model.entity;

import com.codechallenge.product.common.audit.Auditable;
import com.codechallenge.product.inventory.model.enumuration.StarRating;
import com.codechallenge.product.inventory.model.enumuration.VerificationStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Vote extends Auditable {

    @NotNull
    private StarRating rate;

    @NotNull
    private VerificationStatus verificationStatus;
}
