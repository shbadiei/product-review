package com.codechallenge.product.sales.model.entity;

import com.codechallenge.product.sales.model.enumuration.AllowedActor;
import com.codechallenge.product.sales.model.enumuration.Visibility;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class ReviewAccessibilitySetting {
    @NotNull
    private Visibility productVisibility;
    @NotNull
    private AllowedActor voteAllowedActor;
    @NotNull
    private AllowedActor commentAllowedActor;

}
