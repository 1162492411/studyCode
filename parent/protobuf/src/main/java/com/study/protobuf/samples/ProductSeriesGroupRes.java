package com.study.protobuf.samples;

import lombok.Data;

import java.util.List;

/**
 * @Author: zyg
 * @Version: 1.0
 */
@Data
public class ProductSeriesGroupRes {

    private String seriesName;

    private String seriesCode;

    private List<ProductSeriesRes> productSeriesList;

}