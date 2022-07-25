package com.codechallenge.product.dbchangelog;

import com.codechallenge.product.inventory.model.entity.Product;
import com.codechallenge.product.inventory.model.enumuration.ProductCategory;
import com.codechallenge.product.sales.model.entity.ProductSalesInfo;
import com.codechallenge.product.sales.model.entity.Provider;
import com.codechallenge.product.uaa.model.entity.UserInfo;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.Index;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@ChangeLog
@Slf4j
public class CHL14010503 {

    private Map<String, Provider> companyTitle2Provider = new HashMap<>();

    private final static String SAMSERVICE_COMPANY_TITLE = "SAMSERVICE";
    private final static String MADIRAN_COMPANY_TITLE = "MADIRAN";

    @ChangeSet(order = "004", id = "CHL14010503-1-INITIALIZE_PROVIDERS_DATA", author = "sh.badiei")
    public void initializeProvidersData(MongockTemplate mongockTemplate) {
        companyTitle2Provider = mongockTemplate.insertAll(
                Arrays.asList(
                        new Provider()
                                .setCompanyTitle(SAMSERVICE_COMPANY_TITLE)
                                .setStaffUsernames(Arrays.asList("s.sami", "s.samian")),
                        new Provider()
                                .setCompanyTitle(MADIRAN_COMPANY_TITLE)
                                .setStaffUsernames(Collections.singletonList("a.mardani"))
                )
        ).stream().collect(Collectors.toMap(
                        Provider::getCompanyTitle,
                        provider -> provider
                )
        );
    }

    @ChangeSet(order = "005", id = "CHL14010503-2-PROVIDER_UNIQ_IDX", author = "sh.badiei")
    public void createProviderIdx(MongockTemplate mongockTemplate) {
        mongockTemplate.indexOps(Provider.class)
                .ensureIndex(
                        new Index().unique().on("companyTitle", Sort.Direction.ASC)
                );
    }

    @ChangeSet(order = "006", id = "CHL14010503-3-INITIALIZE_INVENTORY_DATA", author = "sh.badiei")
    public void initializeInventoryData(MongockTemplate mongockTemplate) {

        Product product = mongockTemplate.save(
                new Product()
                        .setTitle("Samsung Galaxy A13")
                        .setCategory(ProductCategory.Mobile)

        );
        new ProductSalesInfo()
                .setProductId(product.getId())
                .setProvider(companyTitle2Provider.get(SAMSERVICE_COMPANY_TITLE))
                .setPriceInTomans(4_500_000L);
        mongockTemplate.insertAll(
                Arrays.asList(

                )
        );
    }


}