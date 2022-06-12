package com.study.protobuf.samples;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 型号涨跌信息
 * @author zyg
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductTagVo implements Serializable{

    private String tagName;

    private String tagCode;

    private String iconUrl;

}