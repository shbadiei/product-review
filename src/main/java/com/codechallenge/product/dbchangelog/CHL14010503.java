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



}