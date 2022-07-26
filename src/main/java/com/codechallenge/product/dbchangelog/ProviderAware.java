package com.codechallenge.product.dbchangelog;

import com.codechallenge.product.sales.model.entity.Provider;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProviderAware {

    protected Map<String, Provider> companyTitle2Provider = new HashMap<>();

    protected final static String SAMSERVICE_COMPANY_TITLE = "SAMSERVICE";
    protected final static String MADIRAN_COMPANY_TITLE = "MADIRAN";

    protected void makeSureCompanyTitle2ProviderMapIsFilled(MongockTemplate mongockTemplate) {
        if (companyTitle2Provider.containsKey(SAMSERVICE_COMPANY_TITLE) && companyTitle2Provider.containsKey(MADIRAN_COMPANY_TITLE)) {
            return;
        }

        if (mongockTemplate.count(new Query(), Provider.class) == 0L) {
            throw new RuntimeException("both companyTitle2Provider and Provider-Collection are empty");
        }

        companyTitle2Provider = mongockTemplate.findAll(Provider.class).stream().collect(Collectors.toMap(
                Provider::getCompanyTitle,
                provider -> provider
        ));
    }
}
