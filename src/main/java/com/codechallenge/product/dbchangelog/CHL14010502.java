package com.codechallenge.product.dbchangelog;

import com.codechallenge.product.uaa.model.entity.Role;
import com.codechallenge.product.uaa.model.entity.UserInfo;
import com.codechallenge.product.uaa.util.AppContextUtil;
import com.codechallenge.product.uaa.util.PasswordUtil;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.Index;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ChangeLog
@Slf4j
public class CHL14010502 {

    @ChangeSet(order = "001", id = "CHL14010502-1-INITIALIZE_UAA_DATA", author = "sh.badiei")
    public void initializeUAAData(MongockTemplate mongockTemplate) {
        mongockTemplate.insertAll(
                Arrays.asList(
                        buildActiveUserInfo(
                                "admin",
                                "rsm825Z",
                                "Shahriar Badiei",
                                Collections.singletonList(Role.ADMIN)
                        ),
                        buildActiveUserInfo(
                                "m.rezvani",
                                "csm702Z",
                                "Mohsen Rezvani",
                                Collections.singletonList(Role.PROVIDER_SALES_SPECIALIST)
                        ),
                        buildActiveUserInfo(
                                "r.sadeghi",
                                "hsm957Z",
                                "Reza Sadeghi",
                                Collections.singletonList(Role.CUSTOMER)
                        )
                )
        );
    }

    private UserInfo buildActiveUserInfo(String username, String plainPassword, String fullName, List<Role> roles) {
        return new UserInfo().setUsername(username)
                .setHashedPassword(
                        AppContextUtil.getContext().getBean(PasswordUtil.class).encodePassword(username, plainPassword)
                )
                .setRoles(roles)
                .setActive(true)
                .setFullName(fullName);
    }

    @ChangeSet(order = "002", id = "CHL14010502-2-USERINFO_UNIQ_IDX", author = "sh.badiei")
    public void createUserInfoIdx(MongockTemplate mongockTemplate) {
        mongockTemplate.indexOps(UserInfo.class)
                .ensureIndex(
                        new Index().unique().on("username", Sort.Direction.ASC)
                );
    }

}