package com.codechallenge.product.inventory.dto;

import com.codechallenge.product.sales.model.entity.Vote;
import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProductDto {

    private ObjectId id;

    private String title;

    private List<Vote> votes;

    private Double averageVote;

    private List<CommentDto> lastComments;

}
