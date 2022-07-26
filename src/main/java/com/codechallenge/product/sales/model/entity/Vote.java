package com.codechallenge.product.sales.model.entity;

import com.codechallenge.product.common.audit.Auditable;
import com.codechallenge.product.inventory.model.enumuration.StarRating;
import com.codechallenge.product.inventory.model.enumuration.VerificationStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Vote extends Auditable {

    @NotNull
    private StarRating rate;

    @NotNull
    private VerificationStatus verificationStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        if (StringUtils.isBlank(this.getCreatedBy()) || StringUtils.isBlank(vote.getCreatedBy())) {
            return false;
        }
        return this.getCreatedBy().equalsIgnoreCase(vote.getCreatedBy());
    }
}
