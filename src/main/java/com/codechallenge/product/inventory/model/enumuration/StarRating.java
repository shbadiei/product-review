package com.codechallenge.product.inventory.model.enumuration;

import java.util.List;
import java.util.Objects;

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

    public Double calcAverageRate(List<StarRating> ratings) {
        return ratings.stream().filter(Objects::nonNull).mapToInt(StarRating::getWeight).average().orElse(0.0);
    }
}
