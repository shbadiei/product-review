package com.codechallenge.product.sales.mapper;

import com.codechallenge.product.common.mapper.BaseMapper;
import com.codechallenge.product.inventory.model.enumuration.StarRating;
import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import com.codechallenge.product.sales.model.entity.Comment;
import com.codechallenge.product.sales.model.entity.ProductSalesInfo;
import com.codechallenge.product.sales.model.entity.Vote;
import com.codechallenge.product.sales.repository.CommentRepository;
import com.codechallenge.product.common.util.AppContextUtil;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Mapper
public interface ProductSalesInfoMapper extends BaseMapper<ProductSalesInfo, ProductSalesInfoDto> {
}
