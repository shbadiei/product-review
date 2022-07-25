package com.codechallenge.product.sales.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document("provider")
@Data
@Accessors(chain = true)
public class Provider {

    @Id
    private ObjectId id;

    @NotNull
    private String companyTitle;

    private List<String> staffUsernames;

    private ReviewAccessibilitySetting providerGeneralReviewAccessibilitySetting;
}
