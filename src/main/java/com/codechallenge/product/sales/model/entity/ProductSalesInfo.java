package com.codechallenge.product.sales.model.entity;

import com.codechallenge.product.sales.exception.SalesErrorInfo;
import com.codechallenge.product.sales.exception.SalesException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document("productSalesInfo")
@Data
@Accessors(chain = true)
public class ProductSalesInfo implements Serializable {

    @Id
    private ObjectId id;

    @NotNull
    private ObjectId productId;

    @NotNull
    @DBRef
    private Provider provider;

    private ReviewAccessibilitySetting reviewAccessibilitySetting;

    @Positive
    private Long priceInToman;

    private List<Vote> votes;

    public void addVote(Vote vote) {
        if (CollectionUtils.isEmpty(votes)) {
            votes = new ArrayList<>();
        }
        if (votes.contains(vote)) {
            throw new SalesException(SalesErrorInfo.AlreadyVoteForThisProductSaleInfo);
        }
        votes.add(vote);
    }

}
