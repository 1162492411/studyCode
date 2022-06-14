package com.study.protobuf.demo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.ResourceUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.study.protobuf.model.ProductSeriesGroupResBuild;
import com.study.protobuf.model.UserBuild;
import com.study.protobuf.samples.ProductSeriesGroupRes;
import com.study.protobuf.samples.ProductSeriesRes;
import com.study.protobuf.samples.ProductTagVo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class Demo {
    //本地安装protobuf3.13.0(若安装为其他版本,修改pom.xml中protobuf-java包的版本号,二者保持一致)
    //build : protoc --proto_path=IMPORT_PATH --java_out=DST_DIR path/to/file.proto

    /**
     * 从字符串转换为Java对象(json版)
     * @param input
     * @return
     */
    public static List<ProductSeriesGroupRes> stringToObjectListWithJson(String input){
        TypeReference<List<ProductSeriesGroupRes>> listTypeReference = new TypeReference<List<ProductSeriesGroupRes>>() {};
        return JSON.parseObject(input,listTypeReference);
    }


    /**
     * 示例 - 基础用法
     */
    @SneakyThrows
    public static void basicDemo() {
        //前置准备 : 生成java文件
        // 方式一 : 在.proto目录下执行protoc --java_out=../ User.proto
        // 方式二 : 引入maven插件,通过执行命令来生成
        UserBuild.User.newBuilder().build();
        UserBuild.User user = UserBuild.User.newBuilder().setAge(22).setName("张有志").setComment("这是张有志").build();
        byte[] bytes = user.toByteArray();
        log.info("尝试还原为string:{}",new String(bytes));
        UserBuild.User parseFrom = UserBuild.User.parseFrom(bytes);
        log.info("这里是转换后的结果:{}", JSON.toJSONString(parseFrom));
    }

    /**
     * 工具 - 打印pb对象的字段
     * @param groupRes
     */
    public static void printPbField(ProductSeriesGroupResBuild.ProductSeriesGroupRes groupRes){
        String logPrefix = "【pb-对象打印】";
        if(Objects.isNull(groupRes)){
            log.info("{}传入对象为空",logPrefix);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("\n" + logPrefix);
        stringBuilder.append("分组name:").append(groupRes.getSeriesName()).append("---");
        stringBuilder.append("分组code:").append(groupRes.getSeriesCode()).append("---");
        if(CollectionUtils.isNotEmpty(groupRes.getProductSeriesListList())){
            stringBuilder.append("该分组有").append(groupRes.getProductSeriesListCount()).append("个系列,详情为:\n");
            for (int i = 0; i < groupRes.getProductSeriesListList().size(); i++) {
                ProductSeriesGroupResBuild.ProductSeriesRes seriesRes = groupRes.getProductSeriesListList().get(i);
                stringBuilder.append("--->第").append(i+1).append("个型号详情:");
                stringBuilder.append("型号id:").append(seriesRes.getId()).append("---");
                stringBuilder.append("型号name:").append(seriesRes.getName()).append("---");
                //打印标签
                if(CollectionUtils.isNotEmpty(seriesRes.getProductTagsList())){
                    stringBuilder.append("该系列有").append(seriesRes.getProductTagsCount()).append("个标签,详情为:\n");
                    for (int j = 0; j < seriesRes.getProductTagsList().size(); j++) {
                        ProductSeriesGroupResBuild.ProductTagVo tagVo = seriesRes.getProductTags(j);
                        stringBuilder.append("------->第").append(j+1).append("个标签详情:");
                        stringBuilder.append("标签name:").append(tagVo.getTagName()).append("---");
                        stringBuilder.append("标签code:").append(tagVo.getTagCode()).append("---");
                        stringBuilder.append("标签url:").append(tagVo.getIconUrl());
                    }
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append("\n");
        }
        log.info(stringBuilder.toString());
    }

    /**
     * 示例 - 从文件读取json对象转换为pb对象,计算提升率
     * @param file
     */
    public static void parseFromJsonToPbDemo(File file) {
        String readStr = ResourceUtil.readStr(file.getPath(), StandardCharsets.UTF_8);
        List<ProductSeriesGroupRes> productSeriesGroupResList = stringToObjectListWithJson(readStr);
        int jsonTotalLength = JSON.toJSONString(productSeriesGroupResList).getBytes(StandardCharsets.UTF_8).length;
        log.info("【对象大小-json】json方式总长度为{}",jsonTotalLength);
        List<ProductSeriesGroupResBuild.ProductSeriesGroupRes> pbGroupResList = new ArrayList<>(productSeriesGroupResList.size());
        productSeriesGroupResList.forEach(inputGroup -> {
            //系列的builder对象
            ProductSeriesGroupResBuild.ProductSeriesGroupRes.Builder groupResBuilder = ProductSeriesGroupResBuild.ProductSeriesGroupRes.newBuilder();
            groupResBuilder.setSeriesName(inputGroup.getSeriesName());
            groupResBuilder.setSeriesCode(inputGroup.getSeriesCode());
            //处理型号列表
            List<ProductSeriesRes> inputSeriesList = inputGroup.getProductSeriesList();
            if (CollectionUtils.isNotEmpty(inputSeriesList)) {
                //遍历型号
                for (int i = 0; i < inputSeriesList.size(); i++) {
                    ProductSeriesRes inputSeries = inputSeriesList.get(i);
                    //型号的builder对象
                    ProductSeriesGroupResBuild.ProductSeriesRes.Builder seriesBuilder = ProductSeriesGroupResBuild.ProductSeriesRes.newBuilder();
                    seriesBuilder.setId(inputSeries.getId());
                    seriesBuilder.setName(inputSeries.getName());
                    //处理标签列表
                    List<ProductTagVo> inputTagList = inputSeries.getProductTags();
                    if (CollectionUtils.isNotEmpty(inputTagList)) {
                        for (int j = 0; j < inputTagList.size(); j++) {
                            //遍历标签
                            ProductTagVo inputTag = inputTagList.get(j);
                            ProductSeriesGroupResBuild.ProductTagVo.Builder tagBuilder = ProductSeriesGroupResBuild.ProductTagVo.newBuilder();
                            tagBuilder.setTagCode(inputTag.getTagCode());
                            tagBuilder.setTagName(inputTag.getTagName());
                            tagBuilder.setIconUrl(inputTag.getIconUrl());
                            //为型号设置标签列表
                            seriesBuilder.addProductTags(tagBuilder.build());
                        }
                    }
                    //为系列设置型号列表
                    groupResBuilder.addProductSeriesList(seriesBuilder.build());
                }
            }
            //将系列添加到list中
            pbGroupResList.add(groupResBuilder.build());
        });
        int pbTotalLength = 0;
        for (int i = 0; i < pbGroupResList.size(); i++) {
            pbTotalLength += pbGroupResList.get(i).toByteArray().length;
        }
        log.info("【对象大小】pb方式的对象长度为{}",pbTotalLength);
        BigDecimal top = new BigDecimal((jsonTotalLength - pbTotalLength));
        BigDecimal bottom = new BigDecimal(jsonTotalLength);
        BigDecimal effect = top.divide(bottom, 2, RoundingMode.FLOOR).multiply(new BigDecimal(100));
        log.info("【{}提升率】pb相比json缩小了{}%",file.getName(),effect);
    }

    @SneakyThrows
    public static void main(String[] args) {
        //本地安装protobuf3.13.0(若安装为其他版本,修改pom.xml中protobuf-java包的版本号,二者保持一致)
        //build : protoc --proto_path=IMPORT_PATH --java_out=DST_DIR path/to/file.proto
        //build该module,目的是使json文件编译到classes中
        FileUtil.loopFiles("originJson").forEach(file -> {
            parseFromJsonToPbDemo(file);
        });
        //运行结果
        /**
         * 16:54:37.173 [main] INFO com.study.protobuf.demo.Demo - 【对象大小-json】json方式总长度为19947
         * 16:54:37.240 [main] INFO com.study.protobuf.demo.Demo - 【对象大小】pb方式的对象长度为14727
         * 16:54:37.240 [main] INFO com.study.protobuf.demo.Demo - 【thinkPad.json提升率】pb相比json缩小了26.00%
         * 16:54:37.265 [main] INFO com.study.protobuf.demo.Demo - 【对象大小-json】json方式总长度为44484
         * 16:54:37.271 [main] INFO com.study.protobuf.demo.Demo - 【对象大小】pb方式的对象长度为29531
         * 16:54:37.272 [main] INFO com.study.protobuf.demo.Demo - 【huaShuo.json提升率】pb相比json缩小了33.00%
         * 16:54:37.273 [main] INFO com.study.protobuf.demo.Demo - 【对象大小-json】json方式总长度为181
         * 16:54:37.273 [main] INFO com.study.protobuf.demo.Demo - 【对象大小】pb方式的对象长度为83
         * 16:54:37.273 [main] INFO com.study.protobuf.demo.Demo - 【yota.json提升率】pb相比json缩小了54.00%
         * 16:54:37.282 [main] INFO com.study.protobuf.demo.Demo - 【对象大小-json】json方式总长度为16844
         * 16:54:37.284 [main] INFO com.study.protobuf.demo.Demo - 【对象大小】pb方式的对象长度为10922
         * 16:54:37.284 [main] INFO com.study.protobuf.demo.Demo - 【sanXing.json提升率】pb相比json缩小了35.00%
         * 16:54:37.290 [main] INFO com.study.protobuf.demo.Demo - 【对象大小-json】json方式总长度为8330
         * 16:54:37.291 [main] INFO com.study.protobuf.demo.Demo - 【对象大小】pb方式的对象长度为4726
         * 16:54:37.291 [main] INFO com.study.protobuf.demo.Demo - 【jinLi.json提升率】pb相比json缩小了43.00%
         * 16:54:37.300 [main] INFO com.study.protobuf.demo.Demo - 【对象大小-json】json方式总长度为11159
         * 16:54:37.303 [main] INFO com.study.protobuf.demo.Demo - 【对象大小】pb方式的对象长度为6228
         * 16:54:37.304 [main] INFO com.study.protobuf.demo.Demo - 【vivo.json提升率】pb相比json缩小了44.00%
         * 16:54:37.309 [main] INFO com.study.protobuf.demo.Demo - 【对象大小-json】json方式总长度为15457
         * 16:54:37.310 [main] INFO com.study.protobuf.demo.Demo - 【对象大小】pb方式的对象长度为9902
         * 16:54:37.311 [main] INFO com.study.protobuf.demo.Demo - 【dongZhi.json提升率】pb相比json缩小了35.00%
         * 16:54:37.325 [main] INFO com.study.protobuf.demo.Demo - 【对象大小-json】json方式总长度为38792
         * 16:54:37.329 [main] INFO com.study.protobuf.demo.Demo - 【对象大小】pb方式的对象长度为28138
         * 16:54:37.329 [main] INFO com.study.protobuf.demo.Demo - 【daiEr.json提升率】pb相比json缩小了27.00%
         * 16:54:37.334 [main] INFO com.study.protobuf.demo.Demo - 【对象大小-json】json方式总长度为10302
         * 16:54:37.335 [main] INFO com.study.protobuf.demo.Demo - 【对象大小】pb方式的对象长度为5720
         * 16:54:37.335 [main] INFO com.study.protobuf.demo.Demo - 【oppo.json提升率】pb相比json缩小了44.00%
         * 16:54:37.343 [main] INFO com.study.protobuf.demo.Demo - 【对象大小-json】json方式总长度为39692
         * 16:54:37.344 [main] INFO com.study.protobuf.demo.Demo - 【对象大小】pb方式的对象长度为28489
         * 16:54:37.344 [main] INFO com.study.protobuf.demo.Demo - 【lianXiang.json提升率】pb相比json缩小了28.00%
         * 16:54:37.347 [main] INFO com.study.protobuf.demo.Demo - 【对象大小-json】json方式总长度为13955
         * 16:54:37.348 [main] INFO com.study.protobuf.demo.Demo - 【对象大小】pb方式的对象长度为8437
         * 16:54:37.348 [main] INFO com.study.protobuf.demo.Demo - 【huaWei.json提升率】pb相比json缩小了39.00%
         */
    }

}

