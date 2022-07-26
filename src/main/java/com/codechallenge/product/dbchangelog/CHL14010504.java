package com.codechallenge.product.dbchangelog;

import com.codechallenge.product.inventory.model.entity.Product;
import com.codechallenge.product.inventory.model.enumuration.StarRating;
import com.codechallenge.product.inventory.model.enumuration.VerificationStatus;
import com.codechallenge.product.sales.model.entity.Comment;
import com.codechallenge.product.sales.model.entity.ProductSalesInfo;
import com.codechallenge.product.sales.model.entity.Purchase;
import com.codechallenge.product.sales.model.entity.Vote;
import com.codechallenge.product.uaa.model.entity.UserInfo;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

@ChangeLog
@Slf4j
public class CHL14010504 extends ProviderAware {

    @ChangeSet(order = "007", id = "CHL14010504-1-INSERT-SAMPLE-PURCHASE", author = "sh.badiei")
    public void insertSamplePurchase(MongockTemplate mongockTemplate) {

        makeSureCompanyTitle2ProviderMapIsFilled(mongockTemplate);

        UserInfo mirmardi = mongockTemplate.findOne(
                Query.query(new Criteria("username").is(CHL14010502.CUSTOMER_USERNAMES[0])),
                UserInfo.class
        );

        Product lenovoIdeaPad3 = mongockTemplate.findOne(
                Query.query(new Criteria("title").is(CHL14010502.PRODUCT_TITLES[1])),
                Product.class
        );

        mongockTemplate.save(new Purchase()
                .setBuyer(mirmardi)
                .setOperationDateTime(new Date())
                .setProduct(lenovoIdeaPad3)
                .setPriceInToman(4_500_000L)
                .setProvider(companyTitle2Provider.get(SAMSERVICE_COMPANY_TITLE))
        );

    }

    @ChangeSet(order = "008", id = "CHL14010504-2-INSERT-SAMPLE-VOTES-AND-COMMENTS", author = "sh.badiei")
    public void insertSampleVotesAndComments(MongockTemplate mongockTemplate) {

        ProductSalesInfo a13SAMService = mongockTemplate.findOne(Query.query(Criteria.byExample(
                new ProductSalesInfo()
                        .setProduct(mongockTemplate.findOne(
                                Query.query(new Criteria("title").is(CHL14010502.PRODUCT_TITLES[0])),
                                Product.class
                        ))
                        .setProvider(companyTitle2Provider.get(SAMSERVICE_COMPANY_TITLE))
        )), ProductSalesInfo.class);

        if (a13SAMService == null) {
            throw new RuntimeException("ProductSalesInfo for A13 from SAM SERVICE does not exist");
        }

        buildAndAddVote(a13SAMService, StarRating.ThreeStar, VerificationStatus.Rejected, CHL14010502.CUSTOMER_USERNAMES[0]);
        buildAndAddVote(a13SAMService, StarRating.FiveStar, VerificationStatus.Verified, CHL14010502.CUSTOMER_USERNAMES[1]);
        buildAndAddVoteAnonymously(a13SAMService, StarRating.FiveStar, VerificationStatus.Verified);
        buildAndAddVoteAnonymously(a13SAMService, StarRating.FourStar, VerificationStatus.NotProcessedYet);
        buildAndAddVoteAnonymously(a13SAMService, StarRating.OneStar, VerificationStatus.NotProcessedYet);
        buildAndAddVoteAnonymously(a13SAMService, StarRating.TwoStar, VerificationStatus.Verified);

        a13SAMService = mongockTemplate.save(a13SAMService);

        mongockTemplate.insertAll(
                List.of(
                        buildComment(
                                "خوبه قیمتش بالاست",
                                VerificationStatus.Rejected,
                                CHL14010502.CUSTOMER_USERNAMES[0]
                        ),
                        buildComment(
                                "خیلی خوبه",
                                VerificationStatus.Verified,
                                CHL14010502.CUSTOMER_USERNAMES[1]
                        ),
                        buildCommentAnonymously(
                                "صفحه نمایش کیفیت خوبی داره",
                                VerificationStatus.NotProcessedYet
                        )
                )
        );

    }


    private Comment buildCommentAnonymously(String text, VerificationStatus verificationStatus) {
        return buildComment(text, verificationStatus, null);
    }

    private Comment buildComment(String text, VerificationStatus verificationStatus, String commenterUsername) {
        Comment comment = new Comment()
                .setCommentText(text)
                .setVerificationStatus(verificationStatus);
        comment.setCreatedBy(commenterUsername).setCreationDate(new Date());
        return comment;
    }


    private void buildAndAddVoteAnonymously(
            ProductSalesInfo productSalesInfo,
            StarRating rating,
            VerificationStatus verificationStatus
    ) {
        buildAndAddVote(productSalesInfo, rating, verificationStatus, null);
    }

    private void buildAndAddVote(
            ProductSalesInfo productSalesInfo,
            StarRating rating,
            VerificationStatus verificationStatus,
            String voterUsername) {
        Vote vote = new Vote().setRate(rating).setVerificationStatus(verificationStatus);
        vote.setCreatedBy(voterUsername).setCreationDate(new Date());
        productSalesInfo.addVote(vote);
    }
}