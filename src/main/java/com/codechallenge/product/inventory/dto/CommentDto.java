package com.codechallenge.product.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class CommentDto {

    private String commentText;

    private Date  createdAt;

}
