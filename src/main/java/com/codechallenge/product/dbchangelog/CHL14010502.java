package com.codechallenge.product.dbchangelog;

import com.codechallenge.product.inventory.model.entity.Product;
import com.codechallenge.product.inventory.model.enumuration.ProductCategory;
import com.codechallenge.product.sales.model.entity.ProductSalesInfo;
import com.codechallenge.product.sales.model.entity.Provider;
import com.codechallenge.product.sales.model.entity.ReviewAccessibilitySetting;
import com.codechallenge.product.sales.model.enumuration.AllowedActor;
import com.codechallenge.product.sales.model.enumuration.Visibility;
import com.codechallenge.product.uaa.model.entity.Role;
import com.codechallenge.product.uaa.model.entity.UserInfo;
import com.codechallenge.product.uaa.util.AppContextUtil;
import com.codechallenge.product.uaa.util.PasswordUtil;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ChangeLog
@Slf4j
public class CHL14010502 extends ProviderAware {

    public final static String[] CUSTOMER_USERNAMES = {"r.mirmardi", "n.hematnasab"};
    public final static String[] PRODUCT_TITLES = {"Samsung Galaxy A13", "Lenovo IdeaPad 3"};

    @ChangeSet(order = "001", id = "CHL14010502-1-INITIALIZE_PROVIDERS_DATA", author = "sh.badiei")
    public void initializeProvidersData(MongockTemplate mongockTemplate) {
        companyTitle2Provider = mongockTemplate.insertAll(
                List.of(
                        new Provider(SAMSERVICE_COMPANY_TITLE),
                        new Provider(MADIRAN_COMPANY_TITLE)
                )
        ).stream().collect(Collectors.toMap(
                        Provider::getCompanyTitle,
                        provider -> provider
                )
        );
    }

    @ChangeSet(order = "002", id = "CHL14010502-2-PROVIDER_UNIQ_IDX", author = "sh.badiei")
    public void createProviderIdx(MongockTemplate mongockTemplate) {
        mongockTemplate.indexOps(Provider.class)
                .ensureIndex(
                        new Index().unique().named("companytitle-uniq-idx").on("companyTitle", Sort.Direction.ASC)
                );
    }

    @ChangeSet(order = "003", id = "CHL14010502-3-INITIALIZE_UAA_DATA", author = "sh.badiei")
    public void initializeUAAData(MongockTemplate mongockTemplate) {

        makeSureCompanyTitle2ProviderMapIsFilled(mongockTemplate);

        final String samePassword = "rsm825";

        List<UserInfo> userInfos = List.of(
                buildActiveUserUserInfo(
                        "d.shiroodi",
                        samePassword,
                        SAMSERVICE_COMPANY_TITLE,
                        Collections.singletonList(Role.PROVIDER_SALES_ADMIN)
                ),
                buildActiveUserUserInfo(
                        "m.rezvani",
                        samePassword,
                        SAMSERVICE_COMPANY_TITLE,
                        Collections.singletonList(Role.PROVIDER_SALES_PERSON)
                ),
                buildActiveUserUserInfo(
                        "r.sajadi",
                        samePassword,
                        MADIRAN_COMPANY_TITLE,
                        List.of(Role.PROVIDER_SALES_ADMIN, Role.PROVIDER_SALES_PERSON)
                )
        );

        List<UserInfo> allUserList = new ArrayList<>(userInfos);

        List.of(CUSTOMER_USERNAMES).forEach(username -> allUserList.add(
                        buildCustomerUserInfo(username, samePassword)
                )
        );

        mongockTemplate.insertAll(allUserList);
    }

    private UserInfo buildActiveUserUserInfo(
            String username, String plainPassword,
            String company, List<Role> roles) {
        return new UserInfo().setUsername(username)
                .setHashedPassword(
                        AppContextUtil.getContext().getBean(PasswordUtil.class).encodePassword(username, plainPassword)
                )
                .setRoles(roles)
                .setProvider(StringUtils.isBlank(company) ? null : companyTitle2Provider.get(company))
                .setActive(true);
    }

    private UserInfo buildCustomerUserInfo(String username, String plainPassword) {
        return buildActiveUserUserInfo(username, plainPassword, null, Collections.singletonList(Role.CUSTOMER));
    }

    @ChangeSet(order = "004", id = "CHL14010502-4-USERINFO_UNIQ_IDX", author = "sh.badiei")
    public void createUserInfoIdx(MongockTemplate mongockTemplate) {
        mongockTemplate.indexOps(UserInfo.class)
                .ensureIndex(
                        new Index().unique().named("username-uniq-idx").on("username", Sort.Direction.ASC)
                );
    }

    @ChangeSet(order = "005", id = "CHL14010503-5-INITIALIZE_INVENTORY_DATA", author = "sh.badiei")
    public void initializeInventoryData(MongockTemplate mongockTemplate) {

        makeSureCompanyTitle2ProviderMapIsFilled(mongockTemplate);

        buildAndSaveProductAndItsSalesInfo(
                mongockTemplate,
                PRODUCT_TITLES[0],
                ProductCategory.Mobile,
                companyTitle2Provider.get(SAMSERVICE_COMPANY_TITLE),
                4_500_000L,
                new ReviewAccessibilitySetting()
                        .setCommentAllowedActor(AllowedActor.AllUsers)
                        .setVoteAllowedActor(AllowedActor.AllUsers)
                        .setProductVisibility(Visibility.VISIBLE)
        );

        buildAndSaveProductAndItsSalesInfo(
                mongockTemplate,
                PRODUCT_TITLES[0],
                ProductCategory.Mobile,
                companyTitle2Provider.get(MADIRAN_COMPANY_TITLE),
                4_600_000L,
                new ReviewAccessibilitySetting()
                        .setCommentAllowedActor(AllowedActor.Buyer)
                        .setVoteAllowedActor(AllowedActor.AllUsers)
                        .setProductVisibility(Visibility.VISIBLE)
        );

        buildAndSaveProductAndItsSalesInfo(
                mongockTemplate,
                PRODUCT_TITLES[1],
                ProductCategory.Laptop,
                companyTitle2Provider.get(SAMSERVICE_COMPANY_TITLE),
                18_500_000L,
                new ReviewAccessibilitySetting()
                        .setCommentAllowedActor(AllowedActor.Buyer)
                        .setVoteAllowedActor(AllowedActor.Buyer)
                        .setProductVisibility(Visibility.VISIBLE)
        );

        buildAndSaveProductAndItsSalesInfo(
                mongockTemplate,
                PRODUCT_TITLES[1],
                ProductCategory.Laptop,
                companyTitle2Provider.get(MADIRAN_COMPANY_TITLE),
                17_000_000L,
                new ReviewAccessibilitySetting()
                        .setCommentAllowedActor(AllowedActor.NoOne)
                        .setVoteAllowedActor(AllowedActor.NoOne)
                        .setProductVisibility(Visibility.HIDDEN)
        );
    }

    @ChangeSet(order = "006", id = "CHL14010502-6-PRODUCT_UNIQ_IDX", author = "sh.badiei")
    public void createProductIdx(MongockTemplate mongockTemplate) {
        mongockTemplate.indexOps(Product.class)
                .ensureIndex(
                        new Index().unique().named("title-uniq-idx").on("title", Sort.Direction.ASC)
                );
    }


    private void buildAndSaveProductAndItsSalesInfo(
            MongockTemplate mongockTemplate,
            String productTitle,
            ProductCategory productCategory,
            Provider provider,
            Long priceInToman,
            ReviewAccessibilitySetting reviewSetting) {

        Product product = null;
        Query byTitleQuery = Query.query(new Criteria("title").is(productTitle));
        if (mongockTemplate.exists(byTitleQuery, Product.class)) {
            product = mongockTemplate.findOne(byTitleQuery, Product.class);
        } else {
            product = mongockTemplate.save(
                    new Product()
                            .setTitle(productTitle)
                            .setCategory(productCategory)
            );
        }
        mongockTemplate.save(new ProductSalesInfo()
                .setProductId(product.getId())
                .setProvider(provider)
                .setPriceInToman(priceInToman)
                .setReviewAccessibilitySetting(reviewSetting)
        );
    }


}