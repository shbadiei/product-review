package com.codechallenge.product.common.audit;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Auditable {

    private String createdBy;

    private Date creationDate;

}
