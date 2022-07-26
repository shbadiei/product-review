package com.codechallenge.product.uaa.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    PROVIDER_SALES_ADMIN,
    PROVIDER_SALES_PERSON,
    CUSTOMER
    ;

    @Override
    public String getAuthority() {
        return String.format("ROLE_%s", name());
    }
}
