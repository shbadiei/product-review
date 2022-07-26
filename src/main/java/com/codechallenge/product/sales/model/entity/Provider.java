package com.codechallenge.product.sales.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document("provider")
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Provider {

    public Provider(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    @Id
    private ObjectId id;

    @NotNull
    private String companyTitle;

    private ReviewAccessibilitySetting providerGeneralReviewAccessibilitySetting;
}
