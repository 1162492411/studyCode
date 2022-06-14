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
     * @param fileName
     */
    public static void parseFromJsonToPbDemo(String fileName) {
        String readStr = ResourceUtil.readStr(fileName, StandardCharsets.UTF_8);
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
        log.info("【{}提升率】pb相比json缩小了{}%",fileName,effect);
    }

    @SneakyThrows
    public static void main(String[] args) {
        //本地安装protobuf3.13.0(若安装为其他版本,修改pom.xml中protobuf-java包的版本号,二者保持一致)
        //build : protoc --proto_path=IMPORT_PATH --java_out=DST_DIR path/to/file.proto
        //build该module,目的是使json文件编译到classes中
        FileUtil.loopFiles("originJson").forEach(file -> {
            parseFromJsonToPbDemo(file.getPath());
        });
    }

}

