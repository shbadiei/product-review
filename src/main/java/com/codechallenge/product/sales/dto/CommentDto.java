package com.codechallenge.product.sales.dto;

import com.codechallenge.product.common.audit.Auditable;
import com.codechallenge.product.inventory.model.enumuration.VerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommentDto extends Auditable {

    private ObjectId id;

    private String commentText;

    private VerificationStatus verificationStatus;
}
