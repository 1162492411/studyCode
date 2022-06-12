package com.study.protobuf.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.study.protobuf.model.ProductSeriesGroupResBuild;
import com.study.protobuf.model.UserBuild;
import com.study.protobuf.samples.ProductSeriesGroupRes;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.List;

@Slf4j
public class Demo {
    //build : protoc --proto_path=IMPORT_PATH --java_out=DST_DIR path/to/file.proto

    /**
     * 从其他对象转换为Pb对象
     * @param resList
     * @return
     */
    public List<ProductSeriesGroupRes> fromObjectToPbObject(List<ProductSeriesGroupRes> resList){
        return null;
    }



    /**
     * 从字符串转换为Java对象(json版)
     * @param input
     * @return
     */
    public List<ProductSeriesGroupRes> fromStringToJavaObjectWithJson(String input){
        TypeReference<List<ProductSeriesGroupRes>> listTypeReference = new TypeReference<List<ProductSeriesGroupRes>>() {};
        return JSON.parseObject(input,listTypeReference);
    }

    /**
     * 从字符串转换为Java对象(pb版)
     * @param input
     * @return
     */
    @SneakyThrows
    public ProductSeriesGroupRes fromStringToJavaObjectWithPb(String input){
        return null;
//        return ProductSeriesGroupResBuild.ProductSeriesGroupRes.parseFrom(input.getBytes(Charset.forName("utf-8")));
    }

    @SneakyThrows
    public static void main(String[] args) {
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
}

