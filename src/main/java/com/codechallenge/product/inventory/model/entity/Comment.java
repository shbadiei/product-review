package com.codechallenge.product.inventory.model.entity;

import com.codechallenge.product.common.audit.Auditable;
import com.codechallenge.product.inventory.model.enumuration.VerificationStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document("comment")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment extends Auditable {

    @Id
    private ObjectId id;

    @NotBlank
    private String commentText;

    @DBRef
    private Product product;

    @NotNull
    private VerificationStatus verificationStatus;

}
