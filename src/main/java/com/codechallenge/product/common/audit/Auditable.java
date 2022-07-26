package com.codechallenge.product.common.audit;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Auditable {

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Date creationDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private Date lastModificationDate;

}
