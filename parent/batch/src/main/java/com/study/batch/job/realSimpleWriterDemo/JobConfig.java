package com.study.batch.job.realSimpleWriterDemo;

import com.study.batch.entity.Teacher;
import com.study.batch.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

@Configuration("realSimpleWriterDemo-JobConfig")
@Slf4j
public class JobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private PlatformTransactionManager txManager;

    /**
     * 读取器
     * @return
     */
    @Bean("realSimpleWriterDemo-Reader")
    public ItemReader<User> reader(){
        MyBatisPagingItemReader<User> reader = new MyBatisPagingItemReader<>();
        reader.setSqlSessionFactory(sqlSessionFactory);
        reader.setQueryId("com.study.batch.mapper.UserMapper.selectByBatchPage");
        return reader;
    }

    /**
     * 写入器
     */
    @Bean("realSimpleWriterDemo-Writer")
    public ItemWriter<Teacher> writer(){
        return new Writer();
    }

    /**
     * 编排 - 定义Step,将ItemReader、ItemProcess、ItemWriter编排到一起
     */
    @Bean("realSimpleWriterDemo-Step")
    public Step mybatisPagingStep(){
        DefaultTransactionAttribute txAttribute = new DefaultTransactionAttribute();
        txAttribute.setPropagationBehavior(Propagation.REQUIRES_NEW.value());
        txAttribute.setIsolationLevel(Isolation.DEFAULT.value());
        txAttribute.setTimeout(10);

        TaskletStep step = stepBuilderFactory.get("realSimpleWriterDemo-Step")
            .chunk(3)
            .reader(reader())
            .processor(new Processor())
            .writer(writer())
            .build();
        //配置事务
        step.setTransactionManager(txManager);
        step.setTransactionAttribute(txAttribute);
        return step;
    }

    /**
     * 编排 - 定义Job,将Step编排到一起
     */
    @Bean("realSimpleWriterDemo-Job")
    public Job mybatisPagingJob(){
        return jobBuilderFactory.get("realSimpleWriterDemo-Job")
            .start(mybatisPagingStep())
            .build();
    }

}
