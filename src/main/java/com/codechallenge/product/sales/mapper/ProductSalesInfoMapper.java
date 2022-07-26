package com.codechallenge.product.sales.mapper;

import com.codechallenge.product.common.mapper.BaseMapper;
import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import com.codechallenge.product.sales.model.entity.Comment;
import com.codechallenge.product.sales.model.entity.ProductSalesInfo;
import com.codechallenge.product.sales.repository.CommentRepository;
import com.codechallenge.product.uaa.util.AppContextUtil;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface ProductSalesInfoMapper extends BaseMapper<ProductSalesInfo, ProductSalesInfoDto> {

    default ProductSalesInfoDto toDto(ProductSalesInfo salesInfo){
        CommentRepository repository = AppContextUtil.getContext().getBean(CommentRepository.class);
        CommentMapper commentMapper = AppContextUtil.getContext().getBean(CommentMapper.class);
        ProductSalesInfoDto salesInfoDto = toDto(salesInfo);
        Page<Comment> lastCommentsPage = repository.findLatestCommentsForProductSalesInfo(salesInfo);
        return salesInfoDto
                .setTotalCommentCount(lastCommentsPage.getTotalElements())
                .setLastComments(commentMapper.toDtoList(lastCommentsPage.getContent()));
    }



//    default List<ProductSalesInfoDto> toDtoList(List<ProductSalesInfo> salesInfos) {
//
//        CommentRepository repository = AppContextUtil.getContext().getBean(CommentRepository.class);
//        CommentMapper commentMapper = AppContextUtil.getContext().getBean(CommentMapper.class);
//
//        return salesInfos.stream().map(salesInfo -> {
//            ProductSalesInfoDto salesInfoDto = toDto(salesInfo);
//            Page<Comment> lastCommentsPage = repository.findLatestCommentsForProductSalesInfo(salesInfo);
//            return salesInfoDto
//                    .setTotalCommentCount(lastCommentsPage.getTotalElements())
//                    .setLastComments(commentMapper.toDtoList(lastCommentsPage.getContent()));
//        }).collect(Collectors.toList());
//
//    }

}
