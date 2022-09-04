package com.study.protobuf.demo;

import cn.hutool.core.io.resource.ResourceUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.protobuf.InvalidProtocolBufferException;
import com.study.protobuf.model.ProductSeriesGroupResBuild;
import com.study.protobuf.samples.ProductSeriesGroupRes;
import com.study.protobuf.samples.ProductSeriesRes;
import com.study.protobuf.samples.ProductTagVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author x
 */
@Slf4j
public class DemoHelper {

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
     * 工具 - 将Java对象转换为Protobuf对象
     * @param productSeriesGroupResList
     * @return
     */
    public static List<ProductSeriesGroupResBuild.ProductSeriesGroupRes> convertToPbFromObject(List<ProductSeriesGroupRes> productSeriesGroupResList) {
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
        return pbGroupResList;
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
     * 示例 - 测试fastJson耗时
     * @param file
     * @param counts
     */
    public static void jsonCostTimeAndMemory(File file, int counts){
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        //准备原始数据
        String readStr = ResourceUtil.readStr(file.getPath(), StandardCharsets.UTF_8);
        long startDeSerializeMemory = memoryMXBean.getHeapMemoryUsage().getUsed();
        long startDeSerializeTime = System.currentTimeMillis();
        //反序列化n次
        for (int i = 0; i < counts; i++) {
            stringToObjectListWithJson(readStr);
        }
        //计算耗时
        long endDeSerializeMemory = memoryMXBean.getHeapMemoryUsage().getUsed();
        long endDeSerializeTime = System.currentTimeMillis();
        long costDeSerializeMemory = (endDeSerializeMemory - startDeSerializeMemory) / 1024 / 1024;
        long costDeSerializeTime = endDeSerializeTime - startDeSerializeTime;
        log.info("【耗时-json】{}次反序列化耗时{}ms,内存使用{}MB",counts,costDeSerializeTime,costDeSerializeMemory);
        //准备数据
        List<ProductSeriesGroupRes> jsonList = stringToObjectListWithJson(readStr);
        long startSerializeTime = System.currentTimeMillis();
        long startSerializeMemory = memoryMXBean.getHeapMemoryUsage().getUsed();
        //序列化n次
        for (int i = 0; i < counts; i++) {
            JSON.toJSONString(jsonList);
        }
        //计算耗时
        long endSerializeMemory = memoryMXBean.getHeapMemoryUsage().getUsed();
        long endSerializeTime = System.currentTimeMillis();
        long costSerializeMemory = (endSerializeMemory - startSerializeMemory) / 1024 / 1024;
        long costSerializeTime = endSerializeTime - startSerializeTime;
        log.info("【耗时-json】{}次序列化耗时{}ms,内存使用{}MB",counts,costSerializeTime,costSerializeMemory);
    }

    /**
     * 示例 - 测试protobuf耗时
     * @param file
     * @param counts
     */
    public static void pbCostTimeAndMemory(File file, int counts){
        //准备数据
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        String readStr = ResourceUtil.readStr(file.getPath(), StandardCharsets.UTF_8);
        List<ProductSeriesGroupRes> objectList = stringToObjectListWithJson(readStr);
        long startSerializeMemory = memoryMXBean.getHeapMemoryUsage().getUsed();
        long startSerializeTime = System.currentTimeMillis();
        //序列化n次
        for (int i = 0; i < counts; i++) {
            convertToPbFromObject(objectList).forEach(pbObject -> {
                pbObject.toByteArray();
            });
        }
        long endSerializeMemory = memoryMXBean.getHeapMemoryUsage().getUsed();
        long endSerializeTime = System.currentTimeMillis();
        long costSerializeMemory = (endSerializeMemory - startSerializeMemory) / 1024 / 1024;
        long costSerializeTime = endSerializeTime - startSerializeTime;
        log.info("【耗时-pb】{}次序列化耗时{}ms,内存使用{}MB",counts,costSerializeTime,costSerializeMemory);
        //准备数据
        List<ProductSeriesGroupResBuild.ProductSeriesGroupRes> pbObjectList = convertToPbFromObject(objectList);
        List<byte[]> pbByteList = new ArrayList<>(pbObjectList.size());
        pbObjectList.forEach(pbObject -> {
            pbByteList.add(pbObject.toByteArray());
        });
        //反序列化n次
        long startDeSerializeMemory = memoryMXBean.getHeapMemoryUsage().getUsed();
        long startDeSerializeTime = System.currentTimeMillis();
        for (int i = 0; i < counts; i++) {
            pbByteList.forEach(data -> {
                try {
                    ProductSeriesGroupResBuild.ProductSeriesGroupRes.parseFrom(data);
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            });
        }
        long endDeSerializeMemory = memoryMXBean.getHeapMemoryUsage().getUsed();
        long endDeSerializeTime = System.currentTimeMillis();
        long costDeSerializeMemory = (endDeSerializeMemory - startDeSerializeMemory) / 1024 / 1024;
        long costDeSerializeTime = endDeSerializeTime - startDeSerializeTime;
        log.info("【耗时-pb】{}次反序列化耗时{}ms,内存使用{}MB",counts,costDeSerializeTime,costDeSerializeMemory);
    }


}
