package com.study.batch.job.mybatisPagingReader;

import com.study.batch.entity.User;
import com.study.batch.job.simple.SimpleProcessor;
import com.study.batch.job.simple.SimpleWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @author zyg
 */
@Configuration("mybatisPagingDemo-JobConfig")
@Slf4j
public class MybatisPagingJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @StepScope
    @Bean("mybatisPagingDemo-Reader")
    public ItemReader<User> userAgePagingReader(@Value("#{jobParameters[leftAge]}")Long leftAge){
        MyBatisPagingItemReader reader = new MyBatisPagingItemReader();
        reader.setSqlSessionFactory(sqlSessionFactory);
        reader.setQueryId("com.study.batch.mapper.UserMapper.selectByAgeLeft");
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("ageLeft",(leftAge == null ? 0 : leftAge.intValue()));
        reader.setParameterValues(paramMap);
        reader.setName("mybatisPagingDemo-Reader");
        reader.setPageSize(4);
        return reader;
    }

    /**
     * 编排 - 定义Step,将ItemReader、ItemProcess、ItemWriter编排到一起
     */
    @Bean("mybatisPagingDemo-Step")
    public Step mybatisPagingStep(){
        return  stepBuilderFactory.get("mybatisPagingDemo-Step")
            .chunk(2)
            .reader(userAgePagingReader(null))
            .processor(new SimpleProcessor())
            .writer(new SimpleWriter<>())
            .build();
    }

    /**
     * 编排 - 定义Job,将Step编排到一起
      */
    @Bean("mybatisPagingDemo-Job")
    public Job mybatisPagingJob(){
        return jobBuilderFactory.get("mybatisPagingDemo-Job")
            .start(mybatisPagingStep())
            .build();
    }

}
