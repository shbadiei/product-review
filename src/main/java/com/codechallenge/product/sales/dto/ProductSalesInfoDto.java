package com.codechallenge.product.sales.dto;

import com.codechallenge.product.sales.model.entity.ReviewAccessibilitySetting;
import com.codechallenge.product.sales.model.entity.Vote;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProductSalesInfoDto {

    private ObjectId id;

    private ProviderDto provider;

    private ObjectId productId;

    private ReviewAccessibilitySetting reviewAccessibilitySetting;

    private Long priceInToman;

    @JsonIgnore
    private List<Vote> votes;

    private Double averageVote;

    private List<CommentDto> lastComments;

    private Long totalCommentCount;

}
