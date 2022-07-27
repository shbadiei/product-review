package com.codechallenge.product.sales.mapper;

import com.codechallenge.product.aggregator.mapper.RowLevelSecurityMode;
import com.codechallenge.product.inventory.model.enumuration.StarRating;
import com.codechallenge.product.inventory.model.enumuration.VerificationStatus;
import com.codechallenge.product.sales.dto.ProductSalesInfoDto;
import com.codechallenge.product.sales.model.entity.Comment;
import com.codechallenge.product.sales.model.entity.ProductSalesInfo;
import com.codechallenge.product.sales.model.entity.Vote;
import com.codechallenge.product.sales.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductSalesInfoConverter {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final ProductSalesInfoMapper productSalesInfoMapper;

    public ProductSalesInfoDto convert(RowLevelSecurityMode rlsMode, ProductSalesInfo salesInfo) {
        ProductSalesInfoDto salesInfoDto = productSalesInfoMapper.toDto(salesInfo);
        Page<Comment> lastCommentsPage = commentRepository.findLatestCommentsForProductSalesInfo(
                salesInfo,
                rlsMode == RowLevelSecurityMode.CUSTOMER ? VerificationStatus.Verified : null
        );
        return salesInfoDto
                .setAverageVote(
                        CollectionUtils.isEmpty(salesInfo.getVotes()) ? 0.0 :
                                StarRating.calcAverageRate(
                                        salesInfo.getVotes().stream()
                                                .filter(vote -> rlsMode == RowLevelSecurityMode.SALES || vote.getVerificationStatus() != null && vote.getVerificationStatus() == VerificationStatus.Verified)
                                                .map(Vote::getRate).collect(Collectors.toList())
                                )
                )
                .setTotalCommentCount(lastCommentsPage.getTotalElements())
                .setLastComments(commentMapper.toDtoList(lastCommentsPage.getContent()));
    }

}
