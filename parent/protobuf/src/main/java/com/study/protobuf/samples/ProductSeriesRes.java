package com.study.protobuf.samples;

import lombok.Data;

import java.util.List;

/**
 * @Author: zyg
 * @Version: 1.0
 */
@Data
public class ProductSeriesRes {

    private Integer id;

    private String name;

    private List<ProductTagVo> productTags;

}