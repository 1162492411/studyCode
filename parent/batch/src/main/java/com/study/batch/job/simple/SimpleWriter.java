package com.study.batch.job.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author zyg
 */
@Slf4j
public class SimpleWriter<T> implements ItemWriter<Integer> {
    @Override
    public void write(List<? extends Integer> list) throws Exception {
        log.info("=====\n即将输出{}条data数据",list.size());
        for (Integer  i : list) {
            log.info("模拟保存 : {}", i);
        }
        log.info("=====\n输出data数据完毕");
    }
}
