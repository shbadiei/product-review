package com.codechallenge.product.aggregator.dto;

import com.codechallenge.product.sales.dto.CommentDto;
import com.codechallenge.product.inventory.dto.ProductDto;
import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import com.codechallenge.product.sales.model.entity.Vote;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FullProductInfoDto extends ProductDto {

    private List<Vote> votes;

    private Double averageVote;

    private List<CommentDto> lastComments;

    private ProductSalesInfoDto salesInfo;

}
