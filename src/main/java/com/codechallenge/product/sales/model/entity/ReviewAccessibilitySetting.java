package com.codechallenge.product.sales.model.entity;

import com.codechallenge.product.sales.model.enumuration.AllowedActor;
import com.codechallenge.product.sales.model.enumuration.Visibility;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReviewAccessibilitySetting {

    private Visibility productVisibility;

    private AllowedActor voteAllowedActor;

    private AllowedActor commentAllowedActor;

}
