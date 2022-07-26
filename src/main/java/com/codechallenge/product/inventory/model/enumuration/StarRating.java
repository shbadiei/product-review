package com.codechallenge.product.inventory.model.enumuration;

public enum StarRating {

    OneStar(1),
    TwoStar(2),
    ThreeStar(3),
    FourStar(4),
    FiveStar(5);

    private final Integer weight;

    public Integer getWeight() {
        return weight;
    }

    StarRating(Integer weight) {
        this.weight = weight;
    }
}
