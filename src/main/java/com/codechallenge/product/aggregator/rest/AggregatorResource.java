package com.codechallenge.product.aggregator.rest;

import com.codechallenge.product.aggregator.dto.request.ProductInfoSearchRequestDto;
import com.codechallenge.product.aggregator.dto.response.FullProductInfoDto;
import com.codechallenge.product.aggregator.service.AggregationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aggregation/api/v1")
public class AggregatorResource {

    private final AggregationService aggregationService;

    @GetMapping("/product")
    public ResponseEntity<Page<FullProductInfoDto>> findProducts(ProductInfoSearchRequestDto requestDto) {

        Page<FullProductInfoDto> fullProductInfoDtoPage = aggregationService.find(
                requestDto,
                PageRequest.of(
                        requestDto.getPage(),
                        requestDto.getSize(),
                        requestDto.getSort() == null ? Sort.by(Sort.Direction.ASC,"category") : requestDto.getSort()
                )
        );
        if(fullProductInfoDtoPage.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fullProductInfoDtoPage);
    }

}
