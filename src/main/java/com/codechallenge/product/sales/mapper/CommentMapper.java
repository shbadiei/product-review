package com.codechallenge.product.sales.mapper;

import com.codechallenge.product.common.mapper.BaseMapper;
import com.codechallenge.product.sales.dto.CommentDto;
import com.codechallenge.product.sales.model.entity.Comment;
import org.mapstruct.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment, CommentDto> {
}
