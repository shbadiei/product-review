package com.codechallenge.product.sales.model.entity;

import com.codechallenge.product.sales.model.enumuration.AllowedActor;
import com.codechallenge.product.sales.model.enumuration.Visibility;

public class ReviewAccessibilitySetting {

    private Visibility productVisibility;

    private AllowedActor voteAllowedActor;

    private AllowedActor commentAllowedActor;

}
